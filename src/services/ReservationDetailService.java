package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import models.ReservationDetail;
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
}
