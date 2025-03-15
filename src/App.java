import java.sql.Time;

import models.Avion;
import services.AvionService;

public class App {
    public static void main(String[] args) throws Exception {
        Time time=Time.valueOf("12:00:00");
        System.out.println(time);
    }
}
