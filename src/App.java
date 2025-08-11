import models.ReservationDetail;
import services.PromotionService;
import services.ReservationDetailService;
import util.CustomPart;

public class App {
    public static void main(String[] args)  {
        PromotionService promotionService=new PromotionService();
        try {
            ReservationDetailService reservationDetailService=new ReservationDetailService();
            ReservationDetail detail=reservationDetailService.findById(Long.parseLong("2"));
            CustomPart passeport = new CustomPart();
            passeport.setBytes(detail.getPasseport());
            passeport.setFileName(detail.getNomFichier());
            String filePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\avion-framework\\assets";
            passeport.regenerateFile(filePath);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
;    }
}
