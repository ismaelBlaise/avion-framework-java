package services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dto.PromotionDto;
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
            preparedStatement.setDouble(3, Double.parseDouble(promotionDto.getPourcentage()));
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
}
