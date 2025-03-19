package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import utils.DbConnect;

public class ReservationService {
    public int creerReservation(String dateReservation, int idStatut, int idClasse, int idVol) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int idReservation = -1;

        try {
            System.out.println(dateReservation);
            connection = DbConnect.getConnection();
            String sql = "INSERT INTO reservations (date_reservation, id_statut, id_classe, id_vol) VALUES (?, ?, ?, ?) RETURNING id_reservation";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(dateReservation+":00.00"));
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
}
