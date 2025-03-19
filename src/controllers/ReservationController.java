package controllers;

import java.util.List;

import org.postgresql.util.PSQLException;

import annotation.Controller;
import annotation.Get;
import annotation.Param;
import annotation.ParamObject;
import annotation.Post;
import annotation.Url;
import dto.RechercheDto;
import dto.ReservationDto;
import models.Vol;
import services.AvionService;
import services.CategorieAgeService;
import services.ClasseService;
import services.RechercheService;
import services.ReservationService;
import services.StatutService;
import services.VilleService;
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
        CategorieAgeService categorieAgeService=new CategorieAgeService();
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservations.jsp");
        try {
           try {
            modelAndView.setAttribute("statuts", statutService.getAllStatuts());
            modelAndView.setAttribute("classes", classeService.getAllClasses());
            modelAndView.setAttribute("vol", volService.getVolById(Long.parseLong(reservationDto.getIdVol())));
            int id=reservationService.creerReservation(reservationDto.getDateReservation(), Integer.parseInt(reservationDto.getIdStatut()), Integer.parseInt(reservationDto.getIdClasse()),Integer.parseInt(reservationDto.getIdVol()));
            modelAndView.setAttribute("reservation",id);
            modelAndView.setAttribute("categoriesAge", categorieAgeService.getAllCategoriesAge());
            modelAndView.setAttribute("page", "reservations/reservation-details.jsp");
           } catch (PSQLException e) {
            modelAndView.setAttribute("page", "reservations/reservations.jsp");
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
           }
        } catch (Exception e) {
            
            modelAndView.setAttribute("page", "reservations/reservations.jsp");
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        
        return modelAndView; 
    }

    @Url(url = "vols-reservation-details")
    @Post
    public ModelAndView ajouterDetails(@Param(name = "idReservation") String idReservation,@Param(name = "idCategorieAge") String idCategorieAge,@Param(name = "nb") String nb){
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservation-details.jsp");
        CategorieAgeService categorieAgeService=new CategorieAgeService();
        ReservationService reservationService=new ReservationService();
        try {
            reservationService.ajouterDetails(Integer.parseInt(idReservation),Integer.parseInt(idCategorieAge), Integer.parseInt(nb));
            modelAndView.setAttribute("reservation",Integer.parseInt(idReservation));
            modelAndView.setAttribute("categoriesAge", categorieAgeService.getAllCategoriesAge());
            modelAndView.setAttribute("succes","Detail reservation ajouter avec succes");
        } catch (Exception e) {
            
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }
    


    @Url(url ="vols-recherche-front-form")
    @Get
    public ModelAndView rechercheForm(){
        AvionService avionService=new AvionService();
        VilleService villeService=new VilleService();
        StatutService statutService=new StatutService();
        ModelAndView modelAndView = new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/recherche.jsp");
        try {
            modelAndView.setAttribute("avions",avionService.getAllAvions());
            modelAndView.setAttribute("villes", villeService.getAllVilles());
            modelAndView.setAttribute("statuts", statutService.getAllStatuts());
            // Vol vol = volService.getVolById(Long.parseLong(id));
            // modelAndView.setAttribute("vol", vol);
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }


    @Url(url ="vols-rechercher-front")
    @Post
    public ModelAndView recherche(@ParamObject(name = "recherche") RechercheDto rechercheDto){
        ModelAndView modelAndView = new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/vols.jsp");
        AvionService avionService=new AvionService();
        VilleService villeService=new VilleService();
        StatutService statutService=new StatutService();
        RechercheService rechercheService=new RechercheService();
        try {
            modelAndView.setAttribute("avions",avionService.getAllAvions());
            modelAndView.setAttribute("villes", villeService.getAllVilles());
            modelAndView.setAttribute("statuts", statutService.getAllStatuts());
            List<Vol> vols=rechercheService.rechercher(rechercheDto);
            modelAndView.setAttribute("vols", vols);
        } catch (Exception e) {

            modelAndView.setAttribute("page", "reservations/recherche.jsp");;
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }
}
 