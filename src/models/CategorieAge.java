package models;

public class CategorieAge {
    private int idCategorieAge;
    private String categorie;
    private Integer ageMin;
    private Integer ageMax;

    public CategorieAge() {
    }

    public CategorieAge(int idCategorieAge, String categorie, Integer ageMin, Integer ageMax) {
        this.idCategorieAge = idCategorieAge;
        this.categorie = categorie;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
    }

    public int getIdCategorieAge() {
        return idCategorieAge;
    }

    public void setIdCategorieAge(int idCategorieAge) {
        this.idCategorieAge = idCategorieAge;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    @Override
    public String toString() {
        return "CategorieAge{" +
                "idCategorieAge=" + idCategorieAge +
                ", categorie='" + categorie + '\'' +
                ", ageMin=" + ageMin +
                ", ageMax=" + ageMax +
                '}';
    }
}
