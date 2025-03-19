package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Classe;
import utils.DbConnect;

public class ClasseService {
    
    public List<Classe> getAllClasses() throws Exception {
        List<Classe> classes = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DbConnect.getConnection();
            classes = new ArrayList<>();
            preparedStatement = connection.prepareStatement("SELECT * FROM classes");
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Classe classe = new Classe();
                classe.setIdClasse(resultSet.getLong("id_classe"));
                classe.setClasse(resultSet.getString("classe"));
                classes.add(classe);
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
        return classes;
    }
}
