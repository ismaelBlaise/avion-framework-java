package controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import annotation.Controller;
import annotation.Get;
import annotation.Param;
import annotation.RestApi;
import annotation.Url;
import models.alea.ReservationList;
import services.alea.AleaService;
import util.ModelAndView;

@Controller
public class AleaController {
    
    @Url(url = "alea")
    @Get
    @RestApi
    public ModelAndView alea(@Param(name = "date") String date){
        ModelAndView modelAndView=new ModelAndView("");
        AleaService aleaService=new AleaService();
        try {
            List<ReservationList> reservationLists=aleaService.aleaFonction(LocalDate.parse(date));
            modelAndView.setAttribute("reservations", reservationLists);
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }

        return modelAndView;

    }

}
