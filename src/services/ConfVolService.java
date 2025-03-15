package services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dto.ConfVolDto;
import utils.DbConnect;

public class ConfVolService {
    public void ajouterCaracteristique(ConfVolDto confVolDto) throws Exception{
        PreparedStatement preparedStatement=null;
        try (Connection connection=DbConnect.getConnection()) {
            preparedStatement=connection.prepareStatement("INSERT INTO conf_vol(id_vol, id_classe, id_categorie_age, montant, capacite) VALUES (?,?,?,?,?)"); 
            preparedStatement.setLong(1, Long.parseLong(confVolDto.getIdVol()));
            preparedStatement.setLong(2, Long.parseLong(confVolDto.getIdClasse()));
            preparedStatement.setLong(3, Long.parseLong(confVolDto.getIdCategorieAge()));
            preparedStatement.setDouble(4, Double.parseDouble(confVolDto.getMontant()));
            preparedStatement.setInt(5, Integer.parseInt(confVolDto.getCapacite()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
