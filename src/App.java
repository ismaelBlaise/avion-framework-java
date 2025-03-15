import java.sql.Time;

import models.Avion;
import models.Vol;
import services.AvionService;
import services.VolService;

public class App {
    public static void main(String[] args) throws Exception {
        VolService volService = new VolService();
        Vol vol=new Vol();
        vol.setNumero("VOL-001");
        vol.setDateVol("2021-12-31");
        vol.setHeureDepart("12:00:00");
        vol.setHeureArrive("14:00:00");
        vol.setIdStatut(1L);
        vol.setIdVilleDepart(1L);
        vol.setIdVilleArrive(2L);
        vol.setIdAvion(1L);
        volService.ajouterVol(vol);
        
    }
}
