package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import models.Vol;
import utils.DbConnect;

public class VolService {

    public void ajouterVol(Vol vol) throws Exception {
        try (Connection connection = DbConnect.getConnection()) {
            String query = "INSERT INTO vols (numero, date_vol, heure_depart, heure_arrivee, id_statut, id_ville_depart, id_ville_arrivee, id_avion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vol.getNumero());
            preparedStatement.setDate(2, Date.valueOf(vol.getDateVol()));
            preparedStatement.setTime(3, Time.valueOf(vol.getHeureDepart()+":00"));
            preparedStatement.setTime(4, Time.valueOf(vol.getHeureArrive()+":00"));
            // preparedStatement.setTime(5, Time.valueOf(vol.getHeureReservation()+":00"));
            // preparedStatement.setTime(6, Time.valueOf(vol.getHeureAnnulation()+":00"));
            preparedStatement.setLong(5, vol.getIdStatut());
            preparedStatement.setLong(6, vol.getIdVilleDepart());
            preparedStatement.setLong(7, vol.getIdVilleArrive());
            preparedStatement.setLong(8, vol.getIdAvion());
            // preparedStatement.setLong(11, vol.getIdVol());
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }


    public void ajouterHeureReservation(String id,String heureReservation) throws Exception{
        try (Connection connection = DbConnect.getConnection()) {
            String query = "UPDATE vols SET heure_reservation = ? WHERE id_vol = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTime(1,Time.valueOf(heureReservation+":00"));
            preparedStatement.setInt(2,Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void ajouterHeureAnnulation(String id,String heureAnnulation) throws Exception{
        try (Connection connection = DbConnect.getConnection()) {
            String query = "UPDATE vols SET heure_annulation = ? WHERE id_vol = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTime(1,Time.valueOf(heureAnnulation+":00"));
            preparedStatement.setInt(2,Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public List<Vol> getAllVols() throws Exception {
        List<Vol> vols = new ArrayList<>();
        try (Connection connection = DbConnect.getConnection()) {
            String query = "SELECT * FROM vols";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vol vol = new Vol(
                    resultSet.getLong("id_vol"),
                    resultSet.getString("numero"),
                    resultSet.getString("date_vol"),
                    resultSet.getString("heure_depart"),
                    resultSet.getString("heure_arrivee"),
                    resultSet.getString("heure_reservation"),
                    resultSet.getString("heure_annulation"),
                    resultSet.getLong("id_statut"),
                    resultSet.getLong("id_ville_depart"),
                    resultSet.getLong("id_ville_arrivee"),
                    resultSet.getLong("id_avion")
                );
                vols.add(vol);
            }
        }
        return vols;
    }


    public Vol getVolById(Long id) throws Exception {
        Vol vol = null;
        try (Connection connection = DbConnect.getConnection()) {
            String query = "SELECT * FROM vols WHERE id_vol = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                vol = new Vol(
                    resultSet.getLong("id_vol"),
                    resultSet.getString("numero"),
                    resultSet.getString("date_vol"),
                    resultSet.getString("heure_depart"),
                    resultSet.getString("heure_arrivee"),
                    resultSet.getString("heure_reservation"),
                    resultSet.getString("heure_annulation"),
                    resultSet.getLong("id_statut"),
                    resultSet.getLong("id_ville_depart"),
                    resultSet.getLong("id_ville_arrivee"),
                    resultSet.getLong("id_avion")
                );
            }
        }
        return vol;
    }


    public void updateVol(Vol vol) throws Exception {
        try (Connection connection = DbConnect.getConnection()) {
            String query = "UPDATE vols SET numero = ?, date_vol = ?, heure_depart = ?, heure_arrivee = ?, id_statut = ?, id_ville_depart = ?, id_ville_arrivee = ?, id_avion = ? WHERE id_vol = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vol.getNumero());
            preparedStatement.setDate(2, Date.valueOf(vol.getDateVol()));
            preparedStatement.setTime(3, Time.valueOf(vol.getHeureDepart()));
            preparedStatement.setTime(4, Time.valueOf(vol.getHeureArrive()));
            // preparedStatement.setTime(5, Time.valueOf(vol.getHeureReservation()+":00"));
            // preparedStatement.setTime(6, Time.valueOf(vol.getHeureAnnulation()+":00"));
            preparedStatement.setLong(5, vol.getIdStatut());
            preparedStatement.setLong(6, vol.getIdVilleDepart());
            preparedStatement.setLong(7, vol.getIdVilleArrive());
            preparedStatement.setLong(8, vol.getIdAvion());
            preparedStatement.setLong(9, vol.getIdVol());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteVol(Long id) throws Exception {
        try (Connection connection = DbConnect.getConnection()) {
            String query = "DELETE FROM vols WHERE id_vol = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
