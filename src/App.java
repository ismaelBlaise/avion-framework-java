import java.sql.Time;
import java.util.List;

import models.Avion;
import models.Promotion;
import models.Reservation;
import models.Vol;
import services.AvionService;
import services.PromotionService;
import services.ReservationService;
import services.VolService;

public class App {
    public static void main(String[] args)  {
        PromotionService promotionService=new PromotionService();
        try {
            VolService volService = new VolService();
            List<Vol> vols = volService.getAllVols();
            for (Vol vol : vols) {
                System.out.println("Vol ID: " + vol.getIdVol());
                System.out.println("Numero: " + vol.getNumero());
                System.out.println("Depart: " + vol.getDepart());
                System.out.println("Arrivee: " + vol.getArrivee());
                System.out.println("Fin Reservation: " + vol.getFinReservation());
                System.out.println("Fin Annulation: " + vol.getFinAnnulation());
                System.out.println("ID Statut: " + vol.getIdStatut());
                System.out.println("ID Ville Depart: " + vol.getIdVilleDepart());
                System.out.println("ID Ville Arrive: " + vol.getIdVilleArrive());
                System.out.println("ID Avion: " + vol.getIdAvion());
                System.out.println("-----------------------------");
            }

            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
;    }
}
