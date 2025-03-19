package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Avion;
import utils.DbConnect;

public class AvionService {

    // Ajouter un avion
    public void ajouterAvion(Avion avion) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbConnect.getConnection();
            String query = "INSERT INTO avions (capacite, modele) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, avion.getCapacite());
            preparedStatement.setString(2, avion.getModele());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            // Relancer l'exception après l'avoir capturée
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    // Récupérer tous les avions
    public List<Avion> getAllAvions() throws Exception {
        List<Avion> avions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnect.getConnection();
            String query = "SELECT * FROM avions";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Avion avion = new Avion();
                avion.setIdAvion(resultSet.getLong("id_avion"));
                avion.setCapacite(resultSet.getInt("capacite"));
                avion.setModele(resultSet.getString("modele"));
                avions.add(avion);
            }
        } catch (Exception e) {
            // Relancer l'exception après l'avoir capturée
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return avions;
    }

    // Récupérer un avion par ID
    public Avion getAvionById(int id) throws Exception {
        Avion avion = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnect.getConnection();
            String query = "SELECT * FROM avions WHERE id_avion = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                avion = new Avion();
                avion.setIdAvion(resultSet.getLong("id_avion"));
                avion.setCapacite(resultSet.getInt("capacite"));
                avion.setModele(resultSet.getString("modele"));
            }
        } catch (Exception e) {
            // Relancer l'exception après l'avoir capturée
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return avion;
    }

    // Mettre à jour un avion
    public void updateAvion(Avion avion) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbConnect.getConnection();
            String query = "UPDATE avions SET capacite = ?, modele = ? WHERE id_avion = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, avion.getCapacite());
            preparedStatement.setString(2, avion.getModele());
            preparedStatement.setLong(3, avion.getIdAvion());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            // Relancer l'exception après l'avoir capturée
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    // Supprimer un avion
    public void deleteAvion(int id) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbConnect.getConnection();
            String query = "DELETE FROM avions WHERE id_avion = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            // Relancer l'exception après l'avoir capturée
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
