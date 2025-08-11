package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Role;
import utils.DbConnect;

public class RoleService {

    public Role findById(Long id) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Role role = null;

        try (Connection connection = DbConnect.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT * FROM roles WHERE id_role = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = new Role();
                role = role.toRole(resultSet);
            }
        } catch (Exception e) {
            throw e; 
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }

        return role;
    }

    public List<Role> findAll() throws Exception {
        List<Role> roles = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = DbConnect.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT * FROM roles");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role();
                role = role.toRole(resultSet);
                roles.add(role);
            }
        } catch (Exception e) {
            throw e;  
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();  
            }
        }

        return roles;
    }
}
