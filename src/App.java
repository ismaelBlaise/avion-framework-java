import java.sql.Time;
import java.util.List;

import models.Avion;
import models.Promotion;
import models.Reservation;
import models.ReservationDetail;
import models.Vol;
import services.AvionService;
import services.PromotionService;
import services.ReservationDetailService;
import services.ReservationService;
import services.VolService;
import utils.CustomPart2;

public class App {
    public static void main(String[] args)  {
        PromotionService promotionService=new PromotionService();
        try {
            ReservationDetailService reservationDetailService=new ReservationDetailService();
            ReservationDetail detail=reservationDetailService.findById(Long.parseLong("2"));
            CustomPart2 passeport = new CustomPart2();
            passeport.setBytes(detail.getPasseport());
            passeport.setFileName(detail.getNomFichier());
            String filePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\avion-framework\\assets";
            passeport.regenerateFile(filePath);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
;    }
}
