package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.Reservation;
import models.ReservationDetail;
import models.ReservationDetail2;
import models.Statut;
import models.Vol;
import utils.DbConnect;

public class ReservationService {


    public List<Reservation> findAllByUtilisateur(int id) throws Exception{
        List<Reservation> reservations=findAll();
        List<Reservation> reservationsList=new ArrayList<>();
        for (Reservation reservation : reservations) {
            if(reservation.getIdUtilisateur()==id){
                reservationsList.add(reservation);
            }
        }
        return reservationsList;
    }

    public List<Reservation> findAll() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = DbConnect.getConnection();
            
            String sql = "SELECT r.id_reservation, r.date_reservation, " +
                        "r.id_statut, s.statut as nom_statut, " +
                        "r.id_utilisateur, u.nom as nom_utilisateur, u.prenom as prenom_utilisateur, " +
                        "r.id_vol, v.numero as numero_vol " +
                        "FROM reservations r " +
                        "LEFT JOIN statuts s ON r.id_statut = s.id_statut " +
                        "LEFT JOIN utilisateurs u ON r.id_utilisateur = u.id_utilisateur " +
                        "LEFT JOIN vols v ON r.id_vol = v.id_vol ";
            
            preparedStatement = connection.prepareStatement(sql);
            
            resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setIdReservation(resultSet.getInt("id_reservation"));
                reservation.setDateReservation(resultSet.getTimestamp("date_reservation"));
                reservation.setIdStatut(resultSet.getInt("id_statut"));
                reservation.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                reservation.setIdVol(resultSet.getInt("id_vol"));
                // reservation.setIdClasse(resultSet.getInt("id_classe"));
                
                // Ajout des noms des relations
                reservation.setStatutNom(resultSet.getString("nom_statut"));
                reservation.setUtilisateurNom(resultSet.getString("nom_utilisateur") + " " + 
                                             resultSet.getString("prenom_utilisateur"));
                reservation.setVolNom(resultSet.getString("numero_vol"));
                // reservation.setClasseNom(resultSet.getString("nom_classe"));
                
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Reservation findById(int idReservation) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reservation reservation = null;
    
        try {
            connection = DbConnect.getConnection();
            
            String sql = "SELECT id_reservation, date_reservation, id_statut, id_utilisateur,id_vol FROM reservations WHERE id_reservation = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idReservation);
    
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                reservation = new Reservation();
                reservation.setIdReservation(resultSet.getInt("id_reservation"));
                reservation.setDateReservation(resultSet.getTimestamp("date_reservation"));
                reservation.setIdStatut(resultSet.getInt("id_statut"));
                reservation.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                // reservation.setIdClasse(resultSet.getInt("id_classe"));
                reservation.setIdVol(resultSet.getInt("id_vol"));
            }
            return reservation;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  
        }catch (Exception e) {
            e.printStackTrace();
            throw e;  
        } finally {
            try {
                
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
           
            }
        }
    }

    public int creerReservation(String dateReservation, int idStatut, int idUtilisateur, int idVol) throws Exception {
        int idReservation = -1;
        VolService volService = new VolService();

        // Vérifier si le vol est complet
        if (volService.isVolComplet((long) idVol)) {
            throw new Exception("Le vol est complet");
        }

        Vol vol = volService.getVolById((long) idVol);

        // Convertir la date de réservation envoyée (ex: "2025-08-11T14:30") en LocalDateTime
        LocalDateTime dateRes = LocalDateTime.parse(dateReservation);

        // Convertir les dates de la base
        LocalDateTime departVol = Timestamp.valueOf(vol.getDepart().replace("T", " ")).toLocalDateTime();
        LocalDateTime finReservation = null;
        if (vol.getFinReservation() != null) {
            finReservation = Timestamp.valueOf(vol.getFinReservation().replace("T", " ")).toLocalDateTime();
        }

        // Vérifications logiques
        if (dateRes.isAfter(departVol)) {
            throw new Exception("Impossible de réserver après la date de départ");
        }

        if (finReservation != null) {
            if (dateRes.isAfter(finReservation)) {
                throw new Exception("Impossible de réserver après la fin de réservation");
            }
        }

        // Enregistrer la réservation
        try (Connection connection = DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO reservations (date_reservation, id_statut, id_utilisateur, id_vol) " +
                "VALUES (?, ?, ?, ?) RETURNING id_reservation"
            )) {

            preparedStatement.setTimestamp(1, Timestamp.valueOf(dateRes));
            preparedStatement.setInt(2, idStatut);
            preparedStatement.setInt(3, idUtilisateur);
            preparedStatement.setInt(4, idVol);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    idReservation = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erreur SQL lors de la création de la réservation : " + e.getMessage(), e);
        }

        return idReservation;
    }
    


    public List<ReservationDetail> findAllDetails(int idReservation) throws Exception {
        List<ReservationDetail> reservationDetails = new ArrayList<>();

        String sql = """
            SELECT rd.id_reservation_detail, rd.id_reservation, rd.id_categorie_age, rd.id_classe, 
                rd.prix, rd.passeport, rd.nom_fichier, rd.date_depot,
                ca.categorie, c.classe
            FROM reservation_details rd
            JOIN categories_age ca ON rd.id_categorie_age = ca.id_categorie_age
            JOIN classes c ON rd.id_classe = c.id_classe
            WHERE rd.id_reservation = ?
        """;

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idReservation);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    ReservationDetail detail = new ReservationDetail();
                    detail.setIdReservationDetail(rs.getInt("id_reservation_detail"));
                    detail.setIdReservation(rs.getInt("id_reservation"));
                    detail.setIdCategorieAge(rs.getInt("id_categorie_age"));
                    detail.setIdClasse(rs.getInt("id_classe"));
                    detail.setPrix(rs.getBigDecimal("prix")); // NUMERIC → BigDecimal
                    detail.setPasseport(rs.getBytes("passeport")); // BYTEA → byte[]
                    detail.setNomFichier(rs.getString("nom_fichier"));
                    Timestamp ts = rs.getTimestamp("date_depot");
                    if (ts != null) {
                        detail.setDateDepot(ts.toLocalDateTime());
                    }
                    detail.setCategorieAge(rs.getString("categorie"));
                    detail.setClasse(rs.getString("classe"));

                    reservationDetails.add(detail);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erreur SQL lors de la récupération des détails de réservation : " + e.getMessage(), e);
        }

        return reservationDetails;
    }


    public List<ReservationDetail2> findAllDetails2(int idReservation) throws Exception {
        List<ReservationDetail2> reservationDetails = new ArrayList<>();

        String sql = """
            SELECT rd.id_reservation_detail, rd.id_reservation, rd.id_categorie_age, rd.id_classe, 
                rd.prix, rd.passeport, rd.nom_fichier, rd.date_depot,
                ca.categorie, c.classe
            FROM reservation_details rd
            JOIN categories_age ca ON rd.id_categorie_age = ca.id_categorie_age
            JOIN classes c ON rd.id_classe = c.id_classe
            WHERE rd.id_reservation = ?
        """;

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idReservation);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    ReservationDetail2 detail = new ReservationDetail2();
                    detail.setIdReservationDetail(rs.getInt("id_reservation_detail"));
                    detail.setIdReservation(rs.getInt("id_reservation"));
                    detail.setIdCategorieAge(rs.getInt("id_categorie_age"));
                    detail.setIdClasse(rs.getInt("id_classe"));
                    detail.setPrix(rs.getBigDecimal("prix")); // NUMERIC → BigDecimal
                    detail.setPasseport(rs.getBytes("passeport")); // BYTEA → byte[]
                    detail.setNomFichier(rs.getString("nom_fichier"));
                    detail.setDateDepot(rs.getString("date_depot"));
                   
                    detail.setCategorieAge(rs.getString("categorie"));
                    detail.setClasse(rs.getString("classe"));

                    reservationDetails.add(detail);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erreur SQL lors de la récupération des détails de réservation : " + e.getMessage(), e);
        }

        return reservationDetails;
    }



    public void ajouterDetails(int idReservation, int idClasse, int idCategorieAge, int nb, boolean promotion) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConfVolService confVolService = new ConfVolService();
        VolService volService = new VolService();
        StatutService statutService = new StatutService();
        PromotionService promotionService = new PromotionService();

        try {
            connection = DbConnect.getConnection();
            connection.setAutoCommit(false);  

            Reservation reservation = findById(idReservation);
            double prix = confVolService.recupererPrixParCategorieAge(idCategorieAge, idClasse, reservation.getIdVol());

            int nbSiegeDispoPromo = promotionService.getNombreSiegesPromotion(
                    Long.valueOf(reservation.getIdVol()), Long.valueOf(idClasse))-promotionService.getNombreVolsAvecPromotion(
                    Long.valueOf(reservation.getIdVol()), Long.valueOf(idClasse));
            
            if (promotion && nbSiegeDispoPromo < nb) {
                throw new Exception("Les sièges en promotion sont insuffisants");
            }

            if (promotion && nbSiegeDispoPromo >= nb) {
                double pourcentage = promotionService.getPromotion(
                        Long.valueOf(reservation.getIdVol()), Long.valueOf(idClasse));
                prix -= prix * pourcentage;  
            }

            

            boolean estComplet = volService.isVolComplet(Long.valueOf(reservation.getIdVol()));
            Vol vol = volService.getVolById(Long.valueOf(reservation.getIdVol()));
            if (estComplet) {
                Statut statut = statutService.getByStatut("Non disponible");
                vol.setIdStatut(statut.getIdStatut());
                volService.updateVol(vol);
                throw new Exception("Vol complet");
            }

            int nbSiegeDispo = volService.nbSiegeDispo(Long.valueOf(reservation.getIdVol()), Long.valueOf(idClasse));
            if (nbSiegeDispo < nb) {
                throw new Exception("Nombre de places insuffisant");
            }

            String sql = "INSERT INTO reservation_details " +
                    "(id_reservation, id_categorie_age, id_classe, prix, date_depot) " +
                    "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

            preparedStatement = connection.prepareStatement(sql);

            // Boucle pour insérer 1 ligne par siège
            for (int i = 0; i < nb; i++) {
                preparedStatement.setInt(1, idReservation);
                preparedStatement.setInt(2, idCategorieAge);
                preparedStatement.setInt(3, idClasse);
                preparedStatement.setBigDecimal(4, new java.math.BigDecimal(prix));
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch(); // exécution en lot
            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
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

            Timestamp tsFinAnnulation = rsVol.getTimestamp("fin_annulation");

            if (tsFinAnnulation != null) {
                LocalDateTime finAnnulation = tsFinAnnulation.toLocalDateTime();
                LocalDateTime maintenant = LocalDateTime.now();

                if (maintenant.isAfter(finAnnulation)) {
                    throw new Exception("La date limite d'annulation est dépassée.");
                }
            } else {
                // Si fin_annulation est null, on peut décider si on autorise ou pas
                // Ici on considère que c'est autorisé
            }

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

}
