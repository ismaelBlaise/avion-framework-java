package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import models.Role;
import models.Utilisateur;
import util.CustomSession;
import utils.DbConnect;

public class UtilisateurService {

    public Role login(CustomSession customSession, String email, String password) throws Exception {
        try (Connection connection = DbConnect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utilisateurs WHERE email = ?");
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur = utilisateur.toUtilisateur(resultSet);

                if (utilisateur.getMdp().equals(password)) {
                    preparedStatement.close();
                    resultSet.close();
                    customSession.add("id", utilisateur.getIdUtilisateur());
                    RoleService roleService=new RoleService();
                    return roleService.findById(utilisateur.getIdRole());
                } else {
                    throw new Exception("Mot de passe incorrect");
                }
            } else {
                throw new Exception("Utilisateur non trouvé");
            }
        } catch (Exception e) {
            throw e;
        }
        
    }

    // public void register(String nom, String prenom, String email, String dateNaissance,String idRole,String contact, String mdp) throws Exception {
    //     try (Connection connection = DbConnect.getConnection()) {
            
    //         PreparedStatement checkEmailStmt = connection.prepareStatement("SELECT * FROM utilisateurs WHERE email = ?");
    //         checkEmailStmt.setString(1, email);
    //         ResultSet resultSet = checkEmailStmt.executeQuery();

    //         if (resultSet.next()) {
    //             throw new Exception("Un utilisateur avec cet email existe déjà");
    //         }

            
    //         PreparedStatement checkContactStmt = connection.prepareStatement("SELECT * FROM utilisateurs WHERE contact = ?");
    //         checkContactStmt.setString(1, contact);
    //         resultSet = checkContactStmt.executeQuery();

    //         if (resultSet.next()) {
    //             throw new Exception("Un utilisateur avec ce contact existe déjà");
    //         }

            
    //         String regexContact = "^\\+?\\d{10,15}$";
    //         if (!Pattern.matches(regexContact, contact)) {
    //             throw new Exception("Le format du contact est invalide");
    //         }

    //         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateurs (nom, prenom, email, date_naissance, contact, mdp,id_role) VALUES (?, ?, ?, ?, ?, ?,?)");
    //         preparedStatement.setString(1, nom);
    //         preparedStatement.setString(2, prenom);
    //         preparedStatement.setString(3, email);
    //         preparedStatement.setString(4, dateNaissance);
    //         preparedStatement.setString(5, contact);
    //         preparedStatement.setString(6, mdp);
    //         preparedStatement.setInt(7,Integer.valueOf(idRole));

    //         preparedStatement.executeUpdate();
    //         preparedStatement.close();
    //         connection.commit();

    //     } catch (SQLException e) {
    //         throw new Exception("Erreur lors de l'enregistrement dans la base de données : " + e.getMessage(), e);
    //     } catch (Exception e) {
    //         throw new Exception("Erreur inattendue lors de l'enregistrement : " + e.getMessage(), e);
    //     }
    // }
}
