package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.Statut;
import models.Vol;
import utils.DbConnect;

public class VolService {

    private StatutService statutService;

    public VolService() {
        this.statutService = new StatutService();
    }


    public boolean isVolComplet(Long idVol) throws Exception {
        String sqlCapacite = 
            "SELECT SUM(cf.capacite) AS capacite_totale " +
            "FROM conf_vol cf " +
            "WHERE cf.id_vol = ?";

        String sqlReservations = 
            "SELECT COUNT(*) AS reservations_totales " +
            "FROM reservation_details rd " +
            "JOIN reservations r ON rd.id_reservation = r.id_reservation " +
            "WHERE r.id_vol = ? " +
            "AND r.id_statut != (SELECT id_statut FROM statuts WHERE statut = 'Annulee')";

        int capaciteTotale = 0;
        int reservationsTotales = 0;

        try (Connection connection = DbConnect.getConnection()) {
            // Récupération de la capacité totale
            try (PreparedStatement stmtCapacite = connection.prepareStatement(sqlCapacite)) {
                stmtCapacite.setLong(1, idVol);
                try (ResultSet rs = stmtCapacite.executeQuery()) {
                    if (rs.next()) {
                        capaciteTotale = rs.getInt("capacite_totale");
                    }
                }
            }

            // Récupération du nombre total de sièges réservés
            try (PreparedStatement stmtReservations = connection.prepareStatement(sqlReservations)) {
                stmtReservations.setLong(1, idVol);
                try (ResultSet rs = stmtReservations.executeQuery()) {
                    if (rs.next()) {
                        reservationsTotales = rs.getInt("reservations_totales");
                    }
                }
            }
        }

        return reservationsTotales >= capaciteTotale;
    }



    public int nbSiegeDispo(Long idVol, Long idClasse) throws Exception {
        String sqlCapacite = """
            SELECT SUM(cf.capacite) AS capacite_totale
            FROM conf_vol cf
            WHERE cf.id_vol = ? AND cf.id_classe = ?
        """;

        String sqlReservations = """
            SELECT COUNT(*) AS reservations_totales
            FROM reservation_details rd
            JOIN reservations r ON rd.id_reservation = r.id_reservation
            WHERE r.id_vol = ? 
            AND rd.id_classe = ?
            AND r.id_statut != (
                SELECT id_statut FROM statuts WHERE statut = 'Annulee'
            )
        """;

        int capaciteTotale = 0;
        int reservationsTotales = 0;

        try (Connection connection = DbConnect.getConnection()) {
            // Récupération capacité totale
            try (PreparedStatement stmtCapacite = connection.prepareStatement(sqlCapacite)) {
                stmtCapacite.setLong(1, idVol);
                stmtCapacite.setLong(2, idClasse);
                try (ResultSet rs = stmtCapacite.executeQuery()) {
                    if (rs.next()) {
                        capaciteTotale = rs.getInt("capacite_totale");
                    } else {
                        throw new Exception("Capacité du vol non configurée");
                    }
                }
            }

            // Récupération nombre de sièges déjà réservés
            try (PreparedStatement stmtReservations = connection.prepareStatement(sqlReservations)) {
                stmtReservations.setLong(1, idVol);
                stmtReservations.setLong(2, idClasse);
                try (ResultSet rs = stmtReservations.executeQuery()) {
                    if (rs.next()) {
                        reservationsTotales = rs.getInt("reservations_totales");
                    }
                }
            }
        }

        return capaciteTotale - reservationsTotales;
    }


   
    public boolean isVolCompletAvecPromotions(Long idVol) throws Exception {
        String sqlCapacite = "SELECT SUM(cf.capacite) AS capacite_totale " +
                           "FROM conf_vol cf " +
                           "WHERE cf.id_vol = ?";
        
        String sqlReservations = "SELECT SUM(rd.nb) AS reservations_totales " +
                               "FROM reservation_details rd " +
                               "JOIN reservations r ON rd.id_reservation = r.id_reservation " +
                               "WHERE r.id_vol = ? AND r.id_statut != (SELECT id_statut FROM statuts WHERE statut = 'Annulee')";
        
        String sqlPromotions = "SELECT COUNT(*) AS nb_promotions FROM promotions WHERE id_vol = ?";
        
        int capaciteTotale = 0;
        int reservationsTotales = 0;
        boolean promotionsDisponibles = false;
        
        try (Connection connection = DbConnect.getConnection()) {
            try (PreparedStatement stmtCapacite = connection.prepareStatement(sqlCapacite)) {
                stmtCapacite.setLong(1, idVol);
                try (ResultSet rs = stmtCapacite.executeQuery()) {
                    if (rs.next()) {
                        capaciteTotale = rs.getInt("capacite_totale");
                    }
                }
            }
            
            try (PreparedStatement stmtReservations = connection.prepareStatement(sqlReservations)) {
                stmtReservations.setLong(1, idVol);
                try (ResultSet rs = stmtReservations.executeQuery()) {
                    if (rs.next()) {
                        reservationsTotales = rs.getInt("reservations_totales");
                    }
                }
            }
            
            try (PreparedStatement stmtPromotions = connection.prepareStatement(sqlPromotions)) {
                stmtPromotions.setLong(1, idVol);
                try (ResultSet rs = stmtPromotions.executeQuery()) {
                    if (rs.next()) {
                        promotionsDisponibles = rs.getInt("nb_promotions") > 0;
                    }
                }
            }
        }
        
        if (promotionsDisponibles) {
            return false;
        }
        
        return reservationsTotales >= capaciteTotale;
    }

     
    public List<Vol> getAllVolsDisponible() throws Exception {
        List<Vol> vols = getAllVols();
        Statut statut = statutService.getByStatut("Disponible");
        vols.removeIf(vol -> !statut.getIdStatut().equals(vol.getIdStatut()));
        return vols;
    }

    public void ajouterVol(Vol vol) throws Exception {
        String query = "INSERT INTO vols (numero, depart, arrivee, fin_reservation, fin_annulation, id_statut, id_ville_depart, id_ville_arrivee, id_avion) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vol.getNumero());

            preparedStatement.setTimestamp(2, parseTimestamp(vol.getDepart(), formatter));
            preparedStatement.setTimestamp(3, parseTimestamp(vol.getArrivee(), formatter));
            preparedStatement.setTimestamp(4, parseTimestamp(vol.getFinReservation(), formatter));
            preparedStatement.setTimestamp(5, parseTimestamp(vol.getFinAnnulation(), formatter));

            if (vol.getIdStatut() != null) {
                preparedStatement.setLong(6, vol.getIdStatut());
            } else {
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
            }

            preparedStatement.setLong(7, vol.getIdVilleDepart());
            preparedStatement.setLong(8, vol.getIdVilleArrive());
            preparedStatement.setLong(9, vol.getIdAvion());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    private Timestamp parseTimestamp(String dateStr, SimpleDateFormat formatter) throws ParseException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        java.util.Date parsedDate = formatter.parse(dateStr);
        return new Timestamp(parsedDate.getTime());
    }

    public void ajouterHeureReservation(String id, String heureReservation) throws Exception {
        String query = "UPDATE vols SET fin_reservation = ? WHERE id_vol = ?";
        Vol vol = null;

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            vol = getVolById(Long.parseLong(id));

            if (vol == null) {
                throw new IllegalArgumentException("Vol introuvable pour l'ID : " + id);
            }

            
            // String heureRes = heureReservation.length() == 5 ? heureReservation + ":00" : heureReservation;

            String departStr = vol.getDepart();
            // String dateDepart = departStr.split(" ")[0]; 

             
            LocalDateTime heureDepart = LocalDateTime.parse(departStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime heureResa = LocalDateTime.parse(heureReservation, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            if (heureResa.isBefore(heureDepart)) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(heureResa));
                preparedStatement.setLong(2, Long.parseLong(id));
                preparedStatement.executeUpdate();
            } else {
                throw new IllegalArgumentException("La réservation ne peut pas être effectuée après l'heure de départ.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Erreur d'argument : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void ajouterHeureAnnulation(String id, String heureAnnulation) throws Exception {
        String query = "UPDATE vols SET fin_annulation = ? WHERE id_vol = ?";
        Vol vol = null;

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            vol = getVolById(Long.parseLong(id));

            if (vol == null) {
                throw new IllegalArgumentException("Vol introuvable pour l'ID : " + id);
            }

            // Format attendu pour heureAnnulation : "HH:mm" ou "HH:mm:ss"
            // String heureAnnul = heureAnnulation.length() == 5 ? heureAnnulation + ":00" : heureAnnulation;

            // Extraire la date du départ pour construire LocalDateTime complet
            String departStr = vol.getDepart(); // ex: "2025-08-11 14:30:00"
            // String dateDepart = departStr.split(" ")[0]; // "2025-08-11"

            LocalDateTime heureDepart = LocalDateTime.parse(departStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime heureAnnulDt = LocalDateTime.parse(heureAnnulation, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            if (heureAnnulDt.isBefore(heureDepart)) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(heureAnnulDt));
                preparedStatement.setLong(2, Long.parseLong(id));
                preparedStatement.executeUpdate();
            } else {
                throw new IllegalArgumentException("L'annulation ne peut pas être effectuée après l'heure de départ.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Erreur d'argument : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    
    public List<Vol> getAllVols() throws Exception {
        List<Vol> vols = new ArrayList<>();
        String query = "SELECT v.*, a.modele AS modele_avion " +
                    "FROM vols v " +
                    "JOIN avions a ON v.id_avion = a.id_avion";

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Vol vol = new Vol(
                    resultSet.getLong("id_vol"),
                    resultSet.getString("numero"),
                    resultSet.getString("depart"),
                    resultSet.getString("arrivee"),
                    resultSet.getString("fin_reservation"),
                    resultSet.getString("fin_annulation"),
                    resultSet.getLong("id_statut"),
                    resultSet.getLong("id_ville_depart"),
                    resultSet.getLong("id_ville_arrivee"),
                    resultSet.getLong("id_avion")
                );
                VilleService villeService = new VilleService();
                vol.setVilleDepart(villeService.getById(vol.getIdVilleDepart()).getVille());
                vol.setVilleArrive(villeService.getById(vol.getIdVilleArrive()).getVille());
                vol.setModeleAvion(resultSet.getString("modele_avion"));

                vols.add(vol);
            }
        }
        return vols;
    }


    public Vol getVolById(Long id) throws Exception {
        Vol vol = null;
        String query = "SELECT * FROM vols WHERE id_vol = ?";

        try (Connection connection = DbConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    vol = new Vol(
                            resultSet.getLong("id_vol"),
                        resultSet.getString("numero"),
                        resultSet.getString("depart"),
                        resultSet.getString("arrivee"),
                        resultSet.getString("fin_reservation"),
                        resultSet.getString("fin_annulation"),
                        resultSet.getLong("id_statut"),
                        resultSet.getLong("id_ville_depart"),
                        resultSet.getLong("id_ville_arrivee"),
                        resultSet.getLong("id_avion")
                    );
                }
            }
        }
        return vol;
    }

    public void updateVol(Vol vol) throws Exception {
        String query = "UPDATE vols SET numero = ?, depart = ?, arrivee = ?, id_statut = ?, id_ville_depart = ?, id_ville_arrivee = ?, id_avion = ? WHERE id_vol = ?";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (Connection connection = DbConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vol.getNumero());
            preparedStatement.setTimestamp(2, parseTimestamp(vol.getDepart(), formatter));
            preparedStatement.setTimestamp(3, parseTimestamp(vol.getArrivee(), formatter));
            preparedStatement.setLong(4, vol.getIdStatut());
            preparedStatement.setLong(5, vol.getIdVilleDepart());
            preparedStatement.setLong(6, vol.getIdVilleArrive());
            preparedStatement.setLong(7, vol.getIdAvion());
            preparedStatement.setLong(8, vol.getIdVol());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteVol(Long id) throws Exception {
        String query = "DELETE FROM vols WHERE id_vol = ?";

        try (Connection connection = DbConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
