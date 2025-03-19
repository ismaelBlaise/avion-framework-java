package controllers;

import annotation.Controller;
import annotation.Get;
import annotation.Param;
import annotation.Url;
import services.VolService;
import util.ModelAndView;

@Controller
public class ReservationController {
    private VolService volService=new VolService();

    @Url(url = "vols-disponible")
    @Get
    public ModelAndView volsDisponible(){
        ModelAndView modelAndView = new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/vols.jsp");
        try {
            modelAndView.setAttribute("vols", volService.getAllVolsDisponible());
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }


    @Url(url = "vols-reserver")
    @Get
    public ModelAndView reserverVol(@Param(name = "id") String id){
        ModelAndView modelAndView=new ModelAndView(id);
        return modelAndView;
    }
}
