package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Role {

    private Long idRole;
    private String role;

    // Constructeur
    public Role(Long idRole, String role) {
        this.idRole = idRole;
        this.role = role;
    }

    public Role() {
            //TODO Auto-generated constructor stub
        }
    
        // Getters et setters
        public Long getIdRole() {
            return idRole;
        }
    
        public void setIdRole(Long idRole) {
            this.idRole = idRole;
        }
    
        public String getRole() {
            return role;
        }
    
        public void setRole(String role) {
            this.role = role;
        }
    
    
        public Role toRole(ResultSet resultSet) throws SQLException {
            Role role = new Role();
        
        role.setIdRole(resultSet.getLong("id_role"));
        role.setRole(resultSet.getString("role"));
        return role;
    }
}
