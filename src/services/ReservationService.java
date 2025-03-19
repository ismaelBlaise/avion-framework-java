package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import models.Reservation;
import utils.DbConnect;

public class ReservationService {
    public Reservation findById(int idReservation) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reservation reservation = null;
    
        try {
            connection = DbConnect.getConnection();
            
            String sql = "SELECT id_reservation, date_reservation, id_statut, id_classe, id_vol FROM reservations WHERE id_reservation = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idReservation);
    
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                reservation = new Reservation();
                reservation.setIdReservation(resultSet.getInt("id_reservation"));
                reservation.setDateReservation(resultSet.getTimestamp("date_reservation"));
                reservation.setIdStatut(resultSet.getInt("id_statut"));
                reservation.setIdClasse(resultSet.getInt("id_classe"));
                reservation.setIdVol(resultSet.getInt("id_vol"));
            }
            return reservation;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  
        } finally {
            try {
                
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
           
            }
        }
    }

    public int creerReservation(String dateReservation, int idStatut, int idClasse, int idVol) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int idReservation = -1;

        try {
            String[] tab=dateReservation.split("T");
            String date=tab[0]+" "+tab[1]+":00.000";
            connection = DbConnect.getConnection();
            String sql = "INSERT INTO reservations (date_reservation, id_statut, id_classe, id_vol) VALUES (?, ?, ?, ?) RETURNING id_reservation";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(date));
            preparedStatement.setInt(2, idStatut);
            preparedStatement.setInt(3, idClasse);
            preparedStatement.setInt(4, idVol);

            generatedKeys = preparedStatement.executeQuery();

            if (generatedKeys.next()) {
                idReservation = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idReservation;
    }    


    public void ajouterDetails(int idReservation, int idCategorieAge, int nb) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConfVolService confVolService=new ConfVolService();
        try {
            connection = DbConnect.getConnection();
            Reservation reservation=findById(idReservation);
            double prix=confVolService.recupererPrixParCategorieAge(idCategorieAge, reservation.getIdVol());
            
            prix*=nb;
            String sql = "INSERT INTO reservation_details (id_reservation, id_categorie_age, prix, nb) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
    
            preparedStatement.setInt(1, idReservation);
            preparedStatement.setInt(2, idCategorieAge);
            preparedStatement.setDouble(3, prix);
            preparedStatement.setInt(4, nb);
    
            preparedStatement.executeUpdate();
    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
