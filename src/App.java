import java.sql.Time;

import models.Avion;
import models.Promotion;
import models.Vol;
import services.AvionService;
import services.PromotionService;
import services.VolService;

public class App {
    public static void main(String[] args)  {
        PromotionService promotionService=new PromotionService();
        try {
            VolService volService=new VolService();
            System.out.println(volService.nbSiezeDispo(Long.valueOf(1), Long.valueOf(1)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
;    }
}
