package models.alea;

import java.time.LocalDate;
import java.util.List;

public class ReservationList {
    int idReservationPrix;
    int capaciteInitial;
    int siezeVendu;
    int siezeNonVendu;
    String dateFin;
    List<ReservationPrix2> reservations;
    public int getIdReservationPrix() {
        return idReservationPrix;
    }
    public void setIdReservationPrix(int idReservationPrix) {
        this.idReservationPrix = idReservationPrix;
    }
    public int getCapaciteInitial() {
        return capaciteInitial;
    }
    public void setCapaciteInitial(int capaciteInitial) {
        this.capaciteInitial = capaciteInitial;
    }
    public int getSiezeVendu() {
        return siezeVendu;
    }
    public void setSiezeVendu(int siezeVendu) {
        this.siezeVendu = siezeVendu;
    }
    public int getSiezeNonVendu() {
        return siezeNonVendu;
    }
    public void setSiezeNonVendu(int siezeNonVendu) {
        this.siezeNonVendu = siezeNonVendu;
    }
    public String getDateFin() {
        return dateFin;
    }
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    public List<ReservationPrix2> getReservations() {
        return reservations;
    }
    public void setReservations(List<ReservationPrix2> reservations) {
        this.reservations = reservations;
    }
    
}
