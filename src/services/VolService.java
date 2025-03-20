package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import models.Statut;
import models.Vol;
import utils.DbConnect;

public class VolService {

    private StatutService statutService;

    public VolService() {
        this.statutService = new StatutService();
    }

     
    public List<Vol> getAllVolsDisponible() throws Exception {
        List<Vol> vols = getAllVols();
        Statut statut = statutService.getByStatut("Disponible");
        vols.removeIf(vol -> !statut.getIdStatut().equals(vol.getIdStatut()));
        return vols;
    }

    public void ajouterVol(Vol vol) throws Exception {
        String query = "INSERT INTO vols (numero, date_vol, heure_depart, heure_arrivee, id_statut, id_ville_depart, id_ville_arrivee, id_avion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DbConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vol.getNumero());
            preparedStatement.setDate(2, Date.valueOf(vol.getDateVol()));
            preparedStatement.setTime(3, Time.valueOf(vol.getHeureDepart() + ":00"));
            preparedStatement.setTime(4, Time.valueOf(vol.getHeureArrive() + ":00"));
            preparedStatement.setLong(5, vol.getIdStatut());
            preparedStatement.setLong(6, vol.getIdVilleDepart());
            preparedStatement.setLong(7, vol.getIdVilleArrive());
            preparedStatement.setLong(8, vol.getIdAvion());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void ajouterHeureReservation(String id, String heureReservation) throws Exception {
        String query = "UPDATE vols SET heure_reservation = ? WHERE id_vol = ?";
        Vol vol = null;
        
        try (Connection connection = DbConnect.getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            
            vol = getVolById(Long.parseLong(id));
            
            if (vol != null) {
                if (Time.valueOf(vol.getHeureDepart()).after(Time.valueOf(heureReservation + ":00"))) {
                    preparedStatement.setTime(1, Time.valueOf(heureReservation + ":00"));
                    preparedStatement.setLong(2, Long.parseLong(id));
                    preparedStatement.executeUpdate();
                } else {
                    throw new IllegalArgumentException("La réservation ne peut pas être effectuée apres l'heure de départ.");
                }
            } else {
                throw new IllegalArgumentException("Vol introuvable pour l'ID : " + id);
            }
            
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur d'argument : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void ajouterHeureAnnulation(String id, String heureAnnulation) throws Exception {
        String query = "UPDATE vols SET heure_annulation = ? WHERE id_vol = ?";
        Vol vol=null;
        try (Connection connection = DbConnect.getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            vol = getVolById(Long.parseLong(id));
            
            if (vol != null) {
                if (Time.valueOf(vol.getHeureDepart()).after(Time.valueOf(heureAnnulation + ":00"))) {
                    preparedStatement.setTime(1, Time.valueOf(heureAnnulation + ":00"));
                    preparedStatement.setLong(2, Long.parseLong(id));
                    preparedStatement.executeUpdate();
                } else {
                    throw new IllegalArgumentException("L'annulation ne peut pas être effectuée apres l'heure de départ.");
                }
            } else {
                throw new IllegalArgumentException("Vol introuvable pour l'ID : " + id);
            }
             
            preparedStatement.setTime(1, Time.valueOf(heureAnnulation + ":00"));
            preparedStatement.setLong(2, Long.parseLong(id));
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur de base de données : " + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    
    public List<Vol> getAllVols() throws Exception {
        List<Vol> vols = new ArrayList<>();
        String query = "SELECT * FROM vols";

        try (Connection connection = DbConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
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
        String query = "SELECT * FROM vols WHERE id_vol = ?";

        try (Connection connection = DbConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
        }
        return vol;
    }

    public void updateVol(Vol vol) throws Exception {
        String query = "UPDATE vols SET numero = ?, date_vol = ?, heure_depart = ?, heure_arrivee = ?, id_statut = ?, id_ville_depart = ?, id_ville_arrivee = ?, id_avion = ? WHERE id_vol = ?";

        try (Connection connection = DbConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vol.getNumero());
            preparedStatement.setDate(2, Date.valueOf(vol.getDateVol()));
            preparedStatement.setTime(3, Time.valueOf(vol.getHeureDepart()));
            preparedStatement.setTime(4, Time.valueOf(vol.getHeureArrive()));
            preparedStatement.setLong(5, vol.getIdStatut());
            preparedStatement.setLong(6, vol.getIdVilleDepart());
            preparedStatement.setLong(7, vol.getIdVilleArrive());
            preparedStatement.setLong(8, vol.getIdAvion());
            preparedStatement.setLong(9, vol.getIdVol());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteVol(Long id) throws Exception {
        String query = "DELETE FROM vols WHERE id_vol = ?";

        try (Connection connection = DbConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
