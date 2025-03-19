package controllers;

import annotation.Controller;
import annotation.Get;
import annotation.Param;
import annotation.ParamObject;
import annotation.Post;
import annotation.Url;
import dto.ReservationDto;
import services.ClasseService;
import services.ReservationService;
import services.StatutService;
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


    @Url(url = "vols-reserver-form")
    @Get
    public ModelAndView reserverVol(@Param(name = "id") String id){
        StatutService statutService=new StatutService();
        ClasseService classeService=new ClasseService();
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservations.jsp");
        try {
            modelAndView.setAttribute("statuts", statutService.getAllStatuts());
            modelAndView.setAttribute("classes", classeService.getAllClasses());
            modelAndView.setAttribute("vol", volService.getVolById(Long.parseLong(id)));
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        
        return modelAndView; 
    }


    @Url(url = "vols-reserver")
    @Post
    public ModelAndView reserver(@ParamObject(name = "reservation") ReservationDto reservationDto){
        StatutService statutService=new StatutService();
        ClasseService classeService=new ClasseService();
        ReservationService reservationService=new ReservationService();
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservations.jsp");
        try {
            modelAndView.setAttribute("statuts", statutService.getAllStatuts());
            modelAndView.setAttribute("classes", classeService.getAllClasses());
            modelAndView.setAttribute("vol", volService.getVolById(Long.parseLong(reservationDto.getIdVol())));
            int id=reservationService.creerReservation(reservationDto.getDateReservation(), Integer.parseInt(reservationDto.getIdStatut()), Integer.parseInt(reservationDto.getIdClasse()),Integer.parseInt(reservationDto.getIdVol()));
            modelAndView.setAttribute("reservation",id);
            
            modelAndView.setAttribute("page", "reservations/reservation-details.jsp");
        } catch (Exception e) {
            
            modelAndView.setAttribute("page", "reservations/reservations.jsp");
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        
        return modelAndView; 
    }
    
}
 