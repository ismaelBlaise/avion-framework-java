package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Ville;
import utils.DbConnect;

public class VilleService {
    public List<Ville> getAllVilles() throws Exception {
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        List<Ville> villes=null;
        try {
            villes=new ArrayList<>();
            connection=DbConnect.getConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM villes");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ville ville=new Ville();
                ville.setIdVille(resultSet.getLong("id_ville"));
                ville.setVille(resultSet.getString("ville"));
                villes.add(ville);
            }
            return villes;
            
        } catch (Exception e) {
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
