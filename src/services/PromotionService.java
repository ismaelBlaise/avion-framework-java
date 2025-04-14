package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.PromotionDto;
import models.Promotion;
import utils.DbConnect;

public class PromotionService {
    public void ajouterPromotion(PromotionDto promotionDto) throws Exception{
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        try{
            connection=DbConnect.getConnection();
            preparedStatement=connection.prepareStatement("INSERT INTO promotions(id_vol, id_classe,pourcentage, nb_siege) VALUES (?,?,?,?)"); 
            preparedStatement.setLong(1, Long.parseLong(promotionDto.getIdVol()));
            preparedStatement.setLong(2, Long.parseLong(promotionDto.getIdClasse()));
            preparedStatement.setDouble(3, Double.parseDouble(promotionDto.getPourcentage())/100);
            preparedStatement.setLong(4, Long.parseLong(promotionDto.getNbSieze()));
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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



    

}
