package models;

public class Role {

    private Long idRole;
    private String role;

    // Constructeur
    public Role(Long idRole, String role) {
        this.idRole = idRole;
        this.role = role;
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
}
