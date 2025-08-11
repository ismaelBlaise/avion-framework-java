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
                ville.setVille(resultSet.getString("nom_ville"));
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


    public Ville getById(long idVille) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Ville ville = null;
        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM villes WHERE id_ville = ?");
            preparedStatement.setLong(1, idVille);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ville = new Ville();
                ville.setIdVille(resultSet.getLong("id_ville"));
                ville.setVille(resultSet.getString("nom_ville"));
            }
            return ville;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
