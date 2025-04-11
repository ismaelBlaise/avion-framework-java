import java.sql.Time;

import models.Avion;
import models.Vol;
import services.AvionService;
import services.VolService;

public class App {
    public static void main(String[] args) throws Exception {
        AvionService avionService=new AvionService();
        Avion avion=avionService.getAvionById((long) 1);
        System.out.println(avion.getModele());
        
    }
}
