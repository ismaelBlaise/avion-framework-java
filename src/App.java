import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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

            Locale malagasyLocale = new Locale("fr", "MG");
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(malagasyLocale);
                        DecimalFormatSymbols symbols = ((DecimalFormat)currencyFormat).getDecimalFormatSymbols();
                        symbols.setCurrencySymbol("MGA"); 
                        ((DecimalFormat)currencyFormat).setDecimalFormatSymbols(symbols);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
;    }
}
