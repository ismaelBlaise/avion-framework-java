package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.ConfVolDto;
import models.ConfVol;
import models.alea.CapaciteRestante;
import utils.DbConnect;

public class ConfVolService {

    public void ajouterCaracteristique(ConfVolDto confVolDto) throws Exception {
        PreparedStatement preparedStatement = null;
        PreparedStatement psCapacite = null;
        PreparedStatement psAvion = null;
        ResultSet rsCapacite = null;
        ResultSet rsAvion = null;

        Long idVol = Long.parseLong(confVolDto.getIdVol());
        int nouvelleCapacite = Integer.parseInt(confVolDto.getCapacite());

        try (Connection connection = DbConnect.getConnection()) {
            // 1. Récupérer la capacité totale actuelle configurée sur ce vol
            String sqlCapacite = "SELECT COALESCE(SUM(capacite),0) AS total_capacite FROM conf_vol WHERE id_vol = ?";
            psCapacite = connection.prepareStatement(sqlCapacite);
            psCapacite.setLong(1, idVol);
            rsCapacite = psCapacite.executeQuery();

            int capaciteActuelle = 0;
            if (rsCapacite.next()) {
                capaciteActuelle = rsCapacite.getInt("total_capacite");
            }

            // 2. Récupérer la capacité de l'avion lié au vol
            String sqlAvionCapacite = "SELECT a.capacite FROM avions a "
                                    + "JOIN vols v ON a.id_avion = v.id_avion "
                                    + "WHERE v.id_vol = ?";
            psAvion = connection.prepareStatement(sqlAvionCapacite);
            psAvion.setLong(1, idVol);
            rsAvion = psAvion.executeQuery();

            int capaciteAvion = 0;
            if (rsAvion.next()) {
                capaciteAvion = rsAvion.getInt("capacite");
            } else {
                throw new Exception("Vol ou avion introuvable pour l'id_vol: " + idVol);
            }

            // 3. Vérifier la contrainte capacité
            if (capaciteActuelle + nouvelleCapacite > capaciteAvion) {
                throw new Exception("Capacité totale des configurations (" + (capaciteActuelle + nouvelleCapacite) + 
                                    ") dépasse la capacité de l'avion (" + capaciteAvion + ")");
            }

            // 4. Insertion si ok
            String sqlInsert = "INSERT INTO conf_vol(id_vol, id_classe, id_categorie_age, montant, capacite) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setLong(1, idVol);
            preparedStatement.setLong(2, Long.parseLong(confVolDto.getIdClasse()));
            preparedStatement.setLong(3, Long.parseLong(confVolDto.getIdCategorieAge()));
            preparedStatement.setDouble(4, Double.parseDouble(confVolDto.getMontant()));
            preparedStatement.setInt(5, nouvelleCapacite);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (rsCapacite != null) try { rsCapacite.close(); } catch (Exception ignored) {}
            if (psCapacite != null) try { psCapacite.close(); } catch (Exception ignored) {}
            if (rsAvion != null) try { rsAvion.close(); } catch (Exception ignored) {}
            if (psAvion != null) try { psAvion.close(); } catch (Exception ignored) {}
            if (preparedStatement != null) try { preparedStatement.close(); } catch (Exception ignored) {}
        }
    }



    public double recupererPrixSiStockDisponible(int idClasse, int idVol, LocalDate dateDonnee) throws Exception {
        String sql = """
            SELECT 
                v.id_reservation_prix,
                v.prix_unitaire,
                v.stock_disponible
            FROM vue_stock_billets_date v
            WHERE v.id_vol = ?
            AND v.id_classe = ?
            AND v.date_fin >= ?
            ORDER BY v.date_fin ASC
            LIMIT 1;
        """;
        System.out.println(dateDonnee.toString());
        try (Connection connection = DbConnect.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idVol);
            ps.setInt(2, idClasse);
            ps.setDate(3, java.sql.Date.valueOf(dateDonnee));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id=rs.getInt("id_reservation_prix");
                    double prixUnitaire = rs.getDouble("prix_unitaire");
                    int stockDisponible = rs.getInt("stock_disponible");

                    if (stockDisponible > 0) {
                        return prixUnitaire;
                    } else {
                        List<CapaciteRestante> capaciteRestantes=getCapacitesRestantesAvantDate(idVol,idClasse,dateDonnee);
                        if(capaciteRestantes.isEmpty()){
                            capaciteRestantes=getCapacitesRestantesNonPayeesAvantDate(idVol, idClasse, dateDonnee);
                        }
                        if(!capaciteRestantes.isEmpty()){
                            
                            for (CapaciteRestante capaciteRestante : capaciteRestantes) {
                                
                                updateCapacite(capaciteRestante.getIdReservationPrix(), -capaciteRestante.getCapacite());
                                updateCapacite(id,capaciteRestante.getCapacite());
                                System.out.println(capaciteRestante.getIdReservation()+" "+capaciteRestante.getIdReservationPrix());
                            }
                        }
                        else{
                         throw new Exception("Plus de billets disponibles pour ce vol et cette classe à la date " + dateDonnee);

                        }
                    }
                    
                }
                throw new Exception("Aucun prix configuré pour ce vol et cette classe avant la date " + dateDonnee);
                // if (rs.next()) {

                //     int stock = rs.getInt("stock_disponible");
                //     if (stock > 0) {
                //         return rs.getDouble("prix_unitaire");
                //     } else {
                //         throw new Exception("Plus de billets disponibles pour ce vol et cette classe à la date " + dateDonnee);
                //     }
                // } else {
                //     throw new Exception("Aucun prix configuré pour ce vol et cette classe avant la date " + dateDonnee);
            }
        }
        
    }

    public void annulerReservation(int idReservation) throws Exception {
        Connection connection = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psStatut = null;
        PreparedStatement psVol = null;
        ResultSet rsStatut = null;
        ResultSet rsVol = null;

        try {
            connection = DbConnect.getConnection();

            // 1. Récupérer l'id_statut pour "Annulee"
            String sqlStatut = "SELECT id_statut FROM statuts WHERE statut = ?";
            psStatut = connection.prepareStatement(sqlStatut);
            psStatut.setString(1, "Annulee");
            rsStatut = psStatut.executeQuery();

            if (!rsStatut.next()) {
                throw new Exception("Le statut 'Annulee' n'existe pas dans la base.");
            }
            int idStatutAnnulee = rsStatut.getInt("id_statut");

            // 2. Récupérer la date fin_annulation du vol associé à la réservation
            String sqlVol = """
                SELECT v.fin_annulation 
                FROM reservations r 
                JOIN vols v ON r.id_vol = v.id_vol
                WHERE r.id_reservation = ?
            """;
            psVol = connection.prepareStatement(sqlVol);
            psVol.setInt(1, idReservation);
            rsVol = psVol.executeQuery();

            if (!rsVol.next()) {
                throw new Exception("Réservation ou vol non trouvé pour l'id: " + idReservation);
            }

            // Timestamp tsFinAnnulation = rsVol.getTimestamp("fin_annulation");

            // if (tsFinAnnulation != null) {
            //     LocalDateTime finAnnulation = tsFinAnnulation.toLocalDateTime();
            //     LocalDateTime maintenant = LocalDateTime.now();

            //     if (maintenant.isAfter(finAnnulation)) {
            //         throw new Exception("La date limite d'annulation est dépassée.");
            //     }
            // } else {
            //     // Si fin_annulation est null, on peut décider si on autorise ou pas
            //     // Ici on considère que c'est autorisé
            // }

            // 3. Mettre à jour le statut de la réservation
            String sqlUpdate = "UPDATE reservations SET id_statut = ? WHERE id_reservation = ?";
            psUpdate = connection.prepareStatement(sqlUpdate);
            psUpdate.setInt(1, idStatutAnnulee);
            psUpdate.setInt(2, idReservation);

            int affectedRows = psUpdate.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Aucune réservation trouvée avec l'id " + idReservation);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (rsVol != null) rsVol.close();
                if (psVol != null) psVol.close();
                if (rsStatut != null) rsStatut.close();
                if (psStatut != null) psStatut.close();
                if (psUpdate != null) psUpdate.close();
                if (connection != null) connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public List<CapaciteRestante> getCapacitesRestantesNonPayeesAvantDate(int idVol, int idClasse, LocalDate date) throws SQLException {
        List<CapaciteRestante> result = new ArrayList<>();
        String sql = """
            SELECT rp.id_reservation_prix, 
               COUNT(r.id_reservation) AS capacite_restante
        FROM reservation_prix rp
        LEFT JOIN reservations r
          ON rp.id_vol = r.id_vol
         AND r.id_classe = rp.id_classe
        LEFT JOIN statuts s
          ON r.id_statut = s.id_statut
        WHERE rp.id_vol = ?
          AND rp.id_classe = ?
          AND r.date_reservation <= ?
          AND s.statut IN ('Confirme')
        GROUP BY rp.id_reservation_prix
            """;

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setInt(1, idVol);
            stmt.setInt(2, idClasse);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_reservation_prix");
                    int capaciteRestante = rs.getInt("capacite_restante");
                    result.add(new CapaciteRestante(id, capaciteRestante));
                }
            }
        }
        return result;
    }

    public List<CapaciteRestante> getCapacitesRestantesAvantDate(int idVol, int idClasse, LocalDate date) throws SQLException {
        List<CapaciteRestante> result = new ArrayList<>();
        String sql = """
            SELECT rp.id_reservation_prix,
                (rp.capacite - COALESCE(SUM(vr.nb_sieges),0)) AS capacite_restante
            FROM reservation_prix rp
            LEFT JOIN vue_reservations_prix_utilises vr
            ON rp.id_reservation_prix = vr.id_reservation_prix
            WHERE rp.date_fin < ?
            AND rp.id_vol = ?
            AND rp.id_classe = ?
            GROUP BY rp.id_reservation_prix, rp.capacite
            HAVING (rp.capacite - COALESCE(SUM(vr.nb_sieges),0)) > 0
            """;

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(date));
            stmt.setInt(2, idVol);
            stmt.setInt(3, idClasse);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_reservation_prix");
                    int capaciteRestante = rs.getInt("capacite_restante");
                    result.add(new CapaciteRestante(id, capaciteRestante));
                }
            }
        }
        return result;
    }



    public void updateCapacite(int idReservationPrix, int delta) throws SQLException {
        String sql = "UPDATE reservation_prix " +
                     "SET capacite = capacite + ? " +
                     "WHERE id_reservation_prix = ? " +
                     "AND capacite + ? >= 0"; 
        Connection connection=DbConnect.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, delta);
            ps.setInt(2, idReservationPrix);
            ps.setInt(3, delta);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Impossible de mettre à jour la capacité. Vérifie si l'ID existe ou si la capacité deviendrait négative.");
            }
        }
    }


    public double recupererPrixParCategorieAge(int idCategorieAge,int idClasse, int idVol) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double prix = 0.0;

        try (Connection connection = DbConnect.getConnection()) {
            String sql = "SELECT montant FROM conf_vol WHERE id_categorie_age = ? AND id_vol = ? AND id_classe=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCategorieAge);
            preparedStatement.setInt(2, idVol);
            preparedStatement.setInt(3, idClasse);

             
            resultSet = preparedStatement.executeQuery();
 
            if (resultSet.next()) {
                prix = resultSet.getDouble("montant");
            }else{
                throw  new Exception("Prix non configurer");
            }
        } catch (Exception e) {
            throw e;  
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();  
            }
        }

        return prix;
    }



    public List<ConfVol> recupererConfVolParVol(Long idVol) throws Exception {
        List<ConfVol> listeConfVol = new ArrayList<>();
        String sql = "SELECT id_vol, id_classe, id_categorie_age,montant, capacite FROM conf_vol WHERE id_vol = ?";

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, idVol);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ConfVol confVol = new ConfVol(
                        resultSet.getLong("id_vol"),
                        resultSet.getLong("id_classe"),
                        resultSet.getLong("id_categorie_age"),
                        resultSet.getDouble("montant"),
                        resultSet.getInt("capacite")
                    );
                    listeConfVol.add(confVol);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return listeConfVol;
    }




    public List<ConfVol> recupererConfVolParNumeroVol(String numeroVol) throws Exception {
        List<ConfVol> liste = new ArrayList<>();

        String sql = "SELECT v.numero AS numero_vol, c.classe AS nom_classe, ca.categorie AS categorie_age, " +
                    "cv.montant, cv.capacite " +
                    "FROM conf_vol cv " +
                    "JOIN vols v ON cv.id_vol = v.id_vol " +
                    "JOIN classes c ON cv.id_classe = c.id_classe " +
                    "JOIN categories_age ca ON cv.id_categorie_age = ca.id_categorie_age " +
                    "WHERE v.id_vol = ?";

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, Long.parseLong(numeroVol));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ConfVol dto = new ConfVol(
                        rs.getString("numero_vol"),
                        rs.getString("nom_classe"),
                        rs.getString("categorie_age"),
                        rs.getDouble("montant"),
                        rs.getInt("capacite")
                    );
                    liste.add(dto);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return liste;
    }


    
}
