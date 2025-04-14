package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import models.Reservation;
import models.Vol;
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
                reservation.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                reservation.setIdClasse(resultSet.getInt("id_classe"));
                reservation.setIdVol(resultSet.getInt("id_vol"));
            }
            return reservation;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  
        }catch (Exception e) {
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

    public int creerReservation(String dateReservation, int idStatut,int idUtilisateur, int idClasse, int idVol) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int idReservation = -1;
        Vol vol=null;
        VolService volService=new VolService();
        try {
            vol=volService.getVolById(Long.valueOf(idVol));
            
            String[] tab=dateReservation.split("T");
            String date=tab[0]+" "+tab[1]+":00.000";
            if(Date.valueOf(vol.getDateVol()).before(Date.valueOf(tab[0]))){
                throw new Exception("Impossible de reserver apres la date de depart");
            }
            if(vol.getHeureReservation()==null){
                if(Time.valueOf(vol.getHeureDepart()).before(Time.valueOf(tab[1]+":00"))){
                    throw new Exception("Impossible de reserver apres l'heure de depart");
                }
            }
            else if(Time.valueOf(vol.getHeureReservation()).before(Time.valueOf(tab[1]+":00"))) {
                throw new Exception("Impossible de reserver apres l'heure fin de reservation");
            }
            connection = DbConnect.getConnection();
            String sql = "INSERT INTO reservations (date_reservation, id_statut,id_utilisateur, id_classe, id_vol) VALUES (?,?, ?, ?, ?) RETURNING id_reservation";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(date));
            preparedStatement.setInt(2, idStatut);
            preparedStatement.setInt(3,idUtilisateur);
            preparedStatement.setInt(4, idClasse);
            preparedStatement.setInt(5, idVol);

            generatedKeys = preparedStatement.executeQuery();

            if (generatedKeys.next()) {
                idReservation = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }catch (Exception e) {
            e.printStackTrace();
        throw e;     
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


    public void ajouterDetails(int idReservation, int idClasse,int idCategorieAge, int nb) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConfVolService confVolService=new ConfVolService();
        try {
            connection = DbConnect.getConnection();
            Reservation reservation=findById(idReservation);
            double prix=confVolService.recupererPrixParCategorieAge(idCategorieAge, idClasse,reservation.getIdVol());
            
            
            String sql = "INSERT INTO reservation_details (id_reservation, id_categorie_age,id_classe, prix, nb) VALUES (?, ?, ?,?, ?)";
            preparedStatement = connection.prepareStatement(sql);
    
            preparedStatement.setInt(1, idReservation);
            preparedStatement.setInt(2, idCategorieAge);
            preparedStatement.setInt(3, idClasse);
            preparedStatement.setDouble(4, prix);
            preparedStatement.setInt(5, nb);
    
            preparedStatement.executeUpdate();
    
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
