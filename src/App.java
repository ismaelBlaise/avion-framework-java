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
            ReservationService reservationService=new ReservationService();
            List<Reservation> reservations=reservationService.findAllByUtilisateur(2);
            for (Reservation reservation : reservations) {
                System.out.println(reservation.getVolNom());
            }

            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
;    }
}
