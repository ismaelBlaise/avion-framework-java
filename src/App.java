import models.Avion;
import services.AvionService;

public class App {
    public static void main(String[] args) throws Exception {
        AvionService avionService=new AvionService();
        Avion avion=avionService.getAvionById(3);
        System.out.println(avion.getModele());
    }
}
