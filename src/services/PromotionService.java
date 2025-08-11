package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PromotionDto;
import models.Promotion;
import utils.DbConnect;

public class PromotionService {
    
    public void ajouterPromotion(PromotionDto promotionDto) throws Exception {
        PreparedStatement preparedStatement = null;
        PreparedStatement psCapacite = null;
        PreparedStatement psReservees = null;
        ResultSet rsCapacite = null;
        ResultSet rsReservees = null;
        Connection connection = null;

        Long idVol = Long.parseLong(promotionDto.getIdVol());
        Long idClasse = Long.parseLong(promotionDto.getIdClasse());
        int nbSiegesPromo = Integer.parseInt(promotionDto.getNbSieze());

        try {
            connection = DbConnect.getConnection();

            // 1. Capacité totale conf_vol pour ce vol et cette classe (toutes catégories d'âge)
            String sqlCapacite = "SELECT COALESCE(SUM(capacite),0) AS total_capacite FROM conf_vol WHERE id_vol = ? AND id_classe = ?";
            psCapacite = connection.prepareStatement(sqlCapacite);
            psCapacite.setLong(1, idVol);
            psCapacite.setLong(2, idClasse);
            rsCapacite = psCapacite.executeQuery();

            int capaciteTotale = 0;
            if (rsCapacite.next()) {
                capaciteTotale = rsCapacite.getInt("total_capacite");
            }

            // 2. Nombre de places déjà utilisées en promotion pour ce vol et classe
            // (On suppose que les places déjà en promotion = somme des nb_siege dans promotions)
            String sqlPlacesPromoExistantes = "SELECT COALESCE(SUM(nb_siege),0) AS places_promo_utilisees FROM promotions WHERE id_vol = ? AND id_classe = ?";
            psReservees = connection.prepareStatement(sqlPlacesPromoExistantes);
            psReservees.setLong(1, idVol);
            psReservees.setLong(2, idClasse);
            rsReservees = psReservees.executeQuery();

            int placesPromoUtilisees = 0;
            if (rsReservees.next()) {
                placesPromoUtilisees = rsReservees.getInt("places_promo_utilisees");
            }

            // 3. Vérification que la nouvelle promotion ne dépasse pas la capacité totale disponible
            // En tenant compte que si la promo pour ce vol+classe existe déjà, on remplace, sinon on ajoute.
            // Pour simplifier, si on fait une insertion (pas update), on peut juste vérifier
            // placesPromoUtilisees + nbSiegesPromo <= capaciteTotale
            // Sinon, si tu veux gérer l'update, il faudrait récupérer l'ancien nb_siege et faire diff.

            if (placesPromoUtilisees + nbSiegesPromo > capaciteTotale) {
                throw new Exception("Le nombre de places promotionnelles (" + (placesPromoUtilisees + nbSiegesPromo) + 
                                    ") dépasse la capacité totale (" + capaciteTotale + ") pour cette classe.");
            }

            // 4. Insertion de la promotion
            preparedStatement = connection.prepareStatement("INSERT INTO promotions(id_vol, id_classe, pourcentage, nb_siege) VALUES (?,?,?,?)");
            preparedStatement.setLong(1, idVol);
            preparedStatement.setLong(2, idClasse);
            preparedStatement.setDouble(3, Double.parseDouble(promotionDto.getPourcentage()) / 100);
            preparedStatement.setInt(4, nbSiegesPromo);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (rsCapacite != null) try { rsCapacite.close(); } catch (Exception ignored) {}
            if (psCapacite != null) try { psCapacite.close(); } catch (Exception ignored) {}
            if (rsReservees != null) try { rsReservees.close(); } catch (Exception ignored) {}
            if (psReservees != null) try { psReservees.close(); } catch (Exception ignored) {}
            if (preparedStatement != null) try { preparedStatement.close(); } catch (Exception ignored) {}
            if (connection != null) try { connection.close(); } catch (Exception ignored) {}
        }
    }



    public double getPromotion(Long idVol,Long idClasse) throws Exception{
        String query="SELECT * FROM promotions WHERE id_vol=? AND id_classe=?;";
        try (Connection connection=DbConnect.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            
                stmt.setLong(1,idVol);
                stmt.setLong(2,idClasse);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble("pourcentage");
                    }
                    else{
                        throw new Exception("Aucune promotion disponible");
                    }
                }
        } catch (Exception e) {
            throw e;
        }
    }


    public int getNombreSiegesAvecPromotion(Long idVol,Long idClasse) throws SQLException {
        String query = "SELECT COALESCE(SUM(rd.nb), 0) AS nb_sieges_promotion " +
                      "FROM reservation_details rd " +
                      "JOIN reservations r ON rd.id_reservation = r.id_reservation " +
                      "JOIN conf_vol cv ON rd.id_classe = cv.id_classe " +
                      "              AND rd.id_categorie_age = cv.id_categorie_age " +
                      "              AND r.id_vol = cv.id_vol " +
                      "WHERE r.id_vol = ? " +
                      "  AND rd.id_classe = ? " +
                      "  AND rd.prix < cv.montant " +
                      "  AND r.id_statut != (SELECT id_statut FROM statuts WHERE statut = 'Annulée')";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setLong(1,idVol);
            stmt.setLong(2,idClasse);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("nb_sieges_promotion");
                }
            }
        }
        return 0;
    }



    public List<Promotion> recupererPromotionsParVol(Long idVol) throws Exception {
        List<Promotion> promotions = new ArrayList<>();
        String sql = "SELECT p.id_classe, c.classe AS nom_classe, p.pourcentage, p.nb_siege " +
                    "FROM promotions p " +
                    "JOIN classes c ON p.id_classe = c.id_classe " +
                    "WHERE p.id_vol = ?";

        try (Connection connection = DbConnect.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setLong(1, idVol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Promotion promo = new Promotion(
                        rs.getLong("id_classe"),
                        rs.getString("nom_classe"),
                        rs.getDouble("pourcentage"),
                        rs.getInt("nb_siege")
                    );
                    promotions.add(promo);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return promotions;
    }
    

}
