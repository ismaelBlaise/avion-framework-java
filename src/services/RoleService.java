package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Role;
import utils.DbConnect;

public class RoleService {
    public Role findById(Long id) throws Exception{
        try (Connection connection=DbConnect.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM roles WHERE id_role =?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet=preparedStatement.executeQuery();
            Role role=new Role();
            role=role.toRole(resultSet);
            preparedStatement.close();
            resultSet.close();
            return role;
        } catch (Exception e) {
            throw e;
        }
    }
}
