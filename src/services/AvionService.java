package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Avion;
import utils.DbConnect;

public class AvionService {

    
    public void ajouterAvion(Avion avion) throws Exception {
        try (Connection connection = DbConnect.getConnection()) {
            String query = "INSERT INTO avions (capacite, modele) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, avion.getCapacite());
            preparedStatement.setString(2, avion.getModele());
            preparedStatement.executeUpdate();
        }
    }

    
    public List<Avion> getAllAvions() throws Exception {
        List<Avion> avions = new ArrayList<>();
        try (Connection connection = DbConnect.getConnection()) {
            String query = "SELECT * FROM avions";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Avion avion = new Avion();
                avion.setIdAvion(resultSet.getLong("id_avion"));
                avion.setCapacite(resultSet.getInt("capacite"));
                avion.setModele(resultSet.getString("modele"));
                avions.add(avion);
            }
        }
        return avions;
    }

   
    public Avion getAvionById(int id) throws Exception {
        Avion avion = null;
        try (Connection connection = DbConnect.getConnection()) {
            String query = "SELECT * FROM avions WHERE id_avion = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                avion = new Avion();
                avion.setIdAvion(resultSet.getLong("id_avion"));
                avion.setCapacite(resultSet.getInt("capacite"));
                avion.setModele(resultSet.getString("modele"));
            }
        }
        return avion;
    }

    // Mettre à jour un avion
    public void updateAvion(Avion avion) throws Exception {
        try (Connection connection = DbConnect.getConnection()) {
            String query = "UPDATE avions SET capacite = ?, modele = ? WHERE id_avion = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, avion.getCapacite());
            preparedStatement.setString(2, avion.getModele());
            preparedStatement.setLong(3, avion.getIdAvion());
            preparedStatement.executeUpdate();
        }
    }

    // Supprimer un avion
    public void deleteAvion(int id) throws Exception {
        try (Connection connection = DbConnect.getConnection()) {
            String query = "DELETE FROM avions WHERE id_avion = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}