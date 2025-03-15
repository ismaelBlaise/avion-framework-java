package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.CategorieAge;
import utils.DbConnect;

public class CategorieAgeService {
    public List<CategorieAge> getAllCategoriesAge() throws Exception {
        List<CategorieAge> categories = new ArrayList<>();
        try (Connection connection = DbConnect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories_age");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CategorieAge categorieAge = new CategorieAge();
                categorieAge.setIdCategorieAge(resultSet.getInt("id_categorie_age"));
                categorieAge.setCategorie(resultSet.getString("categorie"));
                categorieAge.setAgeMin(resultSet.getObject("age_min", Integer.class)); 
                categorieAge.setAgeMax(resultSet.getObject("age_max", Integer.class));
                categories.add(categorieAge);
            }
            resultSet.close();
            preparedStatement.close();
            return categories;
        } catch (Exception e) {
            throw e;
        }
    }
}
