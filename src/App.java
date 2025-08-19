import java.time.LocalDate;
import java.util.List;

import models.alea.ReservationPrix;
import services.ConfVolService;
import services.PromotionService;
import services.alea.AleaService;

public class App {
    public static void main(String[] args)  {
        PromotionService promotionService=new PromotionService();
        try {
            AleaService aleaService=new AleaService();
            aleaService.aleaFonction(LocalDate.now().plusDays(2));
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
;    }
}
