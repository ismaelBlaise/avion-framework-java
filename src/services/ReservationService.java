package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.Reservation;
import models.Statut;
import models.Vol;
import utils.DbConnect;

public class ReservationService {

    public List<Reservation> findAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = DbConnect.getConnection();
            
            String sql = "SELECT id_reservation, date_reservation, id_statut, id_utilisateur, id_vol FROM reservations";
            preparedStatement = connection.prepareStatement(sql);
            
            resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setIdReservation(resultSet.getInt("id_reservation"));
                reservation.setDateReservation(resultSet.getTimestamp("date_reservation"));
                reservation.setIdStatut(resultSet.getInt("id_statut"));
                reservation.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                reservation.setIdVol(resultSet.getInt("id_vol"));
                
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
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

    public Reservation findById(int idReservation) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reservation reservation = null;
    
        try {
            connection = DbConnect.getConnection();
            
            String sql = "SELECT id_reservation, date_reservation, id_statut, id_utilisateur,id_vol FROM reservations WHERE id_reservation = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idReservation);
    
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                reservation = new Reservation();
                reservation.setIdReservation(resultSet.getInt("id_reservation"));
                reservation.setDateReservation(resultSet.getTimestamp("date_reservation"));
                reservation.setIdStatut(resultSet.getInt("id_statut"));
                reservation.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                // reservation.setIdClasse(resultSet.getInt("id_classe"));
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

    public int creerReservation(String dateReservation, int idStatut,int idUtilisateur, int idVol) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int idReservation = -1;
        Vol vol=null;
        VolService volService=new VolService();
        try {
            boolean estComplet=volService.isVolComplet(Long.valueOf(idVol));
            if(estComplet){
                throw new Exception("Le vol est complet");
            }
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
            String sql = "INSERT INTO reservations (date_reservation, id_statut,id_utilisateur, id_vol) VALUES (?, ?, ?, ?) RETURNING id_reservation";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(date));
            preparedStatement.setInt(2, idStatut);
            preparedStatement.setInt(3,idUtilisateur);
            // preparedStatement.setInt(4, idClasse);
            preparedStatement.setInt(4, idVol);

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


    public void ajouterDetails(int idReservation, int idClasse,int idCategorieAge, int nb,boolean promotion) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConfVolService confVolService=new ConfVolService();
        VolService volService=new VolService();
        StatutService statutService=new StatutService();
        PromotionService promotionService=new PromotionService();
                
        try {
            connection = DbConnect.getConnection();
            Reservation reservation=findById(idReservation);
            double prix=confVolService.recupererPrixParCategorieAge(idCategorieAge, idClasse,reservation.getIdVol());
            int nbSiezeDispoPromo= promotionService.getNombreSiegesAvecPromotion(Long.valueOf(reservation.getIdVol()),Long.valueOf(idClasse));
            if(promotion &&nbSiezeDispoPromo>=nb){
                double pourcentage=promotionService.getPromotion(Long.valueOf(reservation.getIdVol()), Long.valueOf(idClasse));
                double prixRemise=prix*pourcentage;
                prix-=prixRemise;
            }
            if(promotion && nbSiezeDispoPromo<nb){
                throw new Exception("Les siezes en promotions insufissant");
            }
            boolean estComplet=volService.isVolComplet(Long.valueOf(reservation.getIdVol()));
            Vol vol=volService.getVolById(Long.valueOf(reservation.getIdVol()));
            if(estComplet){
                Statut statut=statutService.getByStatut("Non disponible");
                vol.setIdStatut(statut.getIdStatut());
                volService.updateVol(vol);
                throw new Exception("Vol complet");
            }
            int nbSiezeDispo=volService.nbSiezeDispo(Long.valueOf(reservation.getIdVol()), Long.valueOf(idClasse));
            if(nbSiezeDispo<nb){
                throw new Exception("Nombre de place insuffisant");
            }
            
            String sql = "INSERT INTO reservation_details (id_reservation, id_categorie_age,id_classe, prix, nb) VALUES (?, ?, ?,?, ?)";
            // String sql = "INSERT INTO reservation_details (id_reservation, id_categorie_age, prix, nb) VALUES ( ?, ?,?, ?)";
            
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
