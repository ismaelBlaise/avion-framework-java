package models;

public class ReservationPasseport {
    private int idReservationPasseport;
    private byte[] passeport;
    private String nomFichier;
    private String dateDepot;
    private int idReservation;
    
    
    public ReservationPasseport() {
    }

    public ReservationPasseport(int idReservationPasseport, byte[] passeport, String nomFichier, String dateDepot,
            int idReservation) {
        this.idReservationPasseport = idReservationPasseport;
        this.passeport = passeport;
        this.nomFichier = nomFichier;
        this.dateDepot = dateDepot;
        this.idReservation = idReservation;
    }

    public int getIdReservationPasseport() {
        return idReservationPasseport;
    }
    public void setIdReservationPasseport(int idReservationPasseport) {
        this.idReservationPasseport = idReservationPasseport;
    }
    public byte[] getPasseport() {
        return passeport;
    }
    public void setPasseport(byte[] passeport) {
        this.passeport = passeport;
    }
    public String getNomFichier() {
        return nomFichier;
    }
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }
    public String getDateDepot() {
        return dateDepot;
    }
    public void setDateDepot(String dateDepot) {
        this.dateDepot = dateDepot;
    }
    public int getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
    
}
