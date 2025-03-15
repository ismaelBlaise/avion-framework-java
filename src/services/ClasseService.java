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
        try (Connection connection=DbConnect.getConnection()){
            classes=new ArrayList<>();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM classes");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                Classe classe=new Classe();
                classe.setIdClasse(resultSet.getLong("id_classe"));
                classe.setClasse(resultSet.getString("classe"));
                classes.add(classe);
            }
            preparedStatement.close();
            resultSet.close();
            return classes;
        } catch (Exception e) {
            throw e;
            
        } 
    }
}
