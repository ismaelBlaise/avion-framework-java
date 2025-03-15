package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Statut;
import models.Vol;
import utils.DbConnect;

public class StatutService {
    public List<Statut> getAllStatuts() throws Exception {
        PreparedStatement preparedStatement = null;
        List<Statut> statuts = null;
        try(Connection connection=DbConnect.getConnection()){
            statuts = new ArrayList<>();
            preparedStatement = connection.prepareStatement("SELECT * FROM statuts");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Statut statut = new Statut();
                statut.setIdStatut(resultSet.getLong("id_statut"));
                statut.setStatut(resultSet.getString("statut"));
                statuts.add(statut);
            }
            preparedStatement.close();
            resultSet.close();
            return statuts;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public Statut getStatutById(Long id) throws Exception {
        PreparedStatement preparedStatement = null;
        Statut statut = null;
        try(Connection connection=DbConnect.getConnection()){
            preparedStatement = connection.prepareStatement("SELECT * FROM statuts WHERE id_statut = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                statut = new Statut();
                statut.setIdStatut(resultSet.getLong("id_statut"));
                statut.setStatut(resultSet.getString("statut"));
            }
            preparedStatement.close();
            resultSet.close();
            return statut;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public Statut getByStatut(String statutStr) throws Exception {
        PreparedStatement preparedStatement = null;
        Statut statut = null;
        try(Connection connection=DbConnect.getConnection()){
            preparedStatement = connection.prepareStatement("SELECT * FROM statuts WHERE statut = ?");
            preparedStatement.setString(1,statutStr );
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                statut = new Statut();
                statut.setIdStatut(resultSet.getLong("id_statut"));
                statut.setStatut(resultSet.getString("statut"));
            }
            preparedStatement.close();
            resultSet.close();
            return statut;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Statut findStatutVols(Vol vol) throws Exception{
       
        
            Statut statut =getStatutById(vol.getIdStatut());
        return statut;
    }

}
