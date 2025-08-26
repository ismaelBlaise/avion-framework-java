package models.alea;

public class CapaciteRestante {
        private int idReservationPrix;
        private int capacite;
        private int idReservation;

        public CapaciteRestante(int idReservationPrix, int capacite, int idReservation) {
            this.idReservationPrix = idReservationPrix;
            this.capacite = capacite;
            this.idReservation = idReservation;
        }

        public CapaciteRestante(int idReservationPrix, int capacite) {
            this.idReservationPrix = idReservationPrix;
            this.capacite = capacite;
        }

        public int getIdReservationPrix() {
            return idReservationPrix;
        }

        public int getCapacite() {
            return capacite;
        }

        @Override
        public String toString() {
            return "ID: " + idReservationPrix + ", capacité restante: " + capacite;
        }

        public void setIdReservationPrix(int idReservationPrix) {
            this.idReservationPrix = idReservationPrix;
        }

        public void setCapacite(int capacite) {
            this.capacite = capacite;
        }

        public int getIdReservation() {
            return idReservation;
        }

        public void setIdReservation(int idReservation) {
            this.idReservation = idReservation;
        }
    }
