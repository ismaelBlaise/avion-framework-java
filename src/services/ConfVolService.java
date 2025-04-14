package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.ConfVolDto;
import utils.DbConnect;

public class ConfVolService {

    public void ajouterCaracteristique(ConfVolDto confVolDto) throws Exception {
        PreparedStatement preparedStatement = null;

        try (Connection connection = DbConnect.getConnection()) {
            preparedStatement = connection.prepareStatement("INSERT INTO conf_vol(id_vol, id_classe, id_categorie_age, montant, capacite) VALUES (?,?,?,?,?)");
            preparedStatement.setLong(1, Long.parseLong(confVolDto.getIdVol()));
            preparedStatement.setLong(2, Long.parseLong(confVolDto.getIdClasse()));
            preparedStatement.setLong(3, Long.parseLong(confVolDto.getIdCategorieAge()));
            preparedStatement.setDouble(4, Double.parseDouble(confVolDto.getMontant()));
            preparedStatement.setInt(5, Integer.parseInt(confVolDto.getCapacite()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;   
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();   
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
}
