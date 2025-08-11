package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import annotation.Param;
import models.ReservationDetail;
import util.CustomPart;
import utils.DbConnect;

public class ReservationDetailService {

    public ReservationDetail findById(Long idReservationDetail) throws Exception {
        ReservationDetail detail = null;
        String sql = """
            SELECT id_reservation_detail, id_reservation, id_categorie_age, id_classe,
                   prix, passeport, nom_fichier, date_depot
            FROM reservation_details
            WHERE id_reservation_detail = ?
        """;

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, idReservationDetail);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detail = new ReservationDetail();
                    detail.setIdReservationDetail(rs.getInt("id_reservation_detail")); // peut rester int car champ int dans modèle
                    detail.setIdReservation(rs.getInt("id_reservation"));
                    detail.setIdCategorieAge(rs.getInt("id_categorie_age"));
                    detail.setIdClasse(rs.getInt("id_classe"));
                    detail.setPrix(rs.getBigDecimal("prix"));
                    detail.setPasseport(rs.getBytes("passeport"));
                    detail.setNomFichier(rs.getString("nom_fichier"));

                    Timestamp ts = rs.getTimestamp("date_depot");
                    if (ts != null) {
                        detail.setDateDepot(ts.toLocalDateTime());
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupération du détail de réservation avec id " 
                                + idReservationDetail + ": " + e.getMessage(), e);
        }
        return detail;
    }

    public String generateUniqueFileName(String originalFileName) {
        // Extraire l'extension (ex: ".pdf")
        String extension = "";
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = originalFileName.substring(dotIndex);
        }
        
        // Générer un UUID
        String uniqueName = UUID.randomUUID().toString();
        
        // Retourner nom unique + extension
        return uniqueName + extension;
    }


    public void importerPasseport(Long idReservationDetail, @Param(name = "passport")CustomPart passeport) throws Exception {
        if (idReservationDetail == null || passeport == null || passeport.getBytes() == null || passeport.getFileName() == null) {
            throw new IllegalArgumentException("Paramètres invalides pour l'import du passeport");
        }

        String sql = "UPDATE reservation_details SET passeport = ?, nom_fichier = ?, date_depot = ? WHERE id_reservation_detail = ?";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String originalFileName = passeport.getFileName();
            String uniqueFileName = generateUniqueFileName(originalFileName);

            ps.setBytes(1, passeport.getBytes());       // le contenu
            ps.setString(2, uniqueFileName);     
            ps.setString(2, passeport.getFileName());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(4, idReservationDetail);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Aucun détail de réservation trouvé avec l'id " + idReservationDetail);
            }
        }
    }
}
