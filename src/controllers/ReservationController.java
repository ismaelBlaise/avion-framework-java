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
import models.Reservation;
import models.ReservationDetail;
import models.Statut;
import models.Vol;
import services.AvionService;
import services.CategorieAgeService;
import services.ClasseService;
import services.RechercheService;
import services.ReservationDetailService;
import services.ReservationService;
import services.StatutService;
import services.VilleService;
import services.VolService;
import util.CustomPart;
import util.CustomSession;
import util.ModelAndView;
import utils.CustomPart2;

@Controller
public class ReservationController {
    private VolService volService=new VolService();

    @Url(url = "reservations")
    @Get
    public ModelAndView reservations(CustomSession session){
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservation-list.jsp");
        ReservationService reservationService=new ReservationService();
        try {
            Long id=(Long) session.get("id");
            // System.out.println(id);
            List<Reservation> reservations=reservationService.findAllByUtilisateur(id.intValue());
            modelAndView.setAttribute("reservations",reservations);
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }




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
            modelAndView.setAttribute("statuts", statutService.getAllBySource("reservation"));
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
    public ModelAndView reserver(@ParamObject(name = "reservation") ReservationDto reservationDto,CustomSession session){
        StatutService statutService=new StatutService();
        ClasseService classeService=new ClasseService();
        ReservationService reservationService=new ReservationService();
        CategorieAgeService categorieAgeService=new CategorieAgeService();
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservations.jsp");
        try {
           try {
            List<Statut> statuts=statutService.getAllBySource("reservation");
            modelAndView.setAttribute("statuts", statuts);
            // if(reservationDto.getIdStatut()==null || reservationDto.getIdStatut().isEmpty()){
            //     throw new Exception("Le statut de la reservation est obligatoire");
            // }
            for (Statut statut : statuts) {
                if(statut.getStatut().equals("Confirme")) {
                    reservationDto.setIdStatut(statut.getIdStatut().toString());
                  
                }
            }
            modelAndView.setAttribute("classes", classeService.getAllClasses());
            modelAndView.setAttribute("vol", volService.getVolById(Long.parseLong(reservationDto.getIdVol())));
            Long idUtilisateur=(Long) session.get("id");
            int id=reservationService.creerReservation(reservationDto.getDateReservation(), Integer.parseInt(reservationDto.getIdStatut()), idUtilisateur.intValue(),Integer.parseInt(reservationDto.getIdVol()));
            modelAndView.setAttribute("reservation",id);
            // modelAndView.setAttribute("classe", classeService.findById(Integer.parseInt(reservationDto.getIdClasse())));
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


    @Url(url = "detail-form")
    @Get
    public ModelAndView detailsForm(@Param(name = "id") String id, CustomSession session){
        StatutService statutService=new StatutService();
        ClasseService classeService=new ClasseService();
        CategorieAgeService categorieAgeService=new CategorieAgeService();
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservation-details.jsp");
        try {
           try {
            modelAndView.setAttribute("statuts", statutService.getAllBySource("vols"));
            modelAndView.setAttribute("classes", classeService.getAllClasses());
            modelAndView.setAttribute("reservation",Integer.parseInt(id));
            // modelAndView.setAttribute("classe", classeService.findById(Integer.parseInt(reservationDto.getIdClasse())));
            modelAndView.setAttribute("categoriesAge", categorieAgeService.getAllCategoriesAge());
            
           } catch (PSQLException e) {
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
           }
        } catch (Exception e) {
            
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        
        return modelAndView; 
    }


    @Url(url = "import-form")
    @Get
    public ModelAndView importForm(@Param(name = "id") String id, CustomSession session){
    
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/import.jsp");
        try {
           
            ReservationDetailService reservationDetailService=new ReservationDetailService();
            ReservationDetail detail=reservationDetailService.findById(Long.parseLong(id));
            modelAndView.setAttribute("detail", detail);
            modelAndView.setAttribute("reservation", detail.getIdReservation());
        } catch (Exception e) {
            
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        
        return modelAndView; 
    }


    @Url(url = "import")
    @Get
    public ModelAndView importer(@Param(name = "id") String id,@Param(name = "passport")CustomPart passport, CustomSession session){
    
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/import.jsp");
        try {
           
            ReservationDetailService reservationDetailService=new ReservationDetailService();
            ReservationDetail detail=reservationDetailService.findById(Long.parseLong(id));
            modelAndView.setAttribute("detail", detail);
            modelAndView.setAttribute("reservation", detail.getIdReservation());
            reservationDetailService.importerPasseport(Long.parseLong(id), passport);
            modelAndView.setAttribute("succes", "Passeport importé avec succès");
        } catch (Exception e) {
            
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        
        return modelAndView; 
    }


    @Url(url = "voir-passport")
    @Get
    public ModelAndView voirPassport(@Param(name = "id") String id,CustomSession session){
    
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/passport.jsp");
        try {
           
            ReservationDetailService reservationDetailService=new ReservationDetailService();
            ReservationDetail detail=reservationDetailService.findById(Long.parseLong(id));
            CustomPart passeport = new CustomPart();
            passeport.setBytes(detail.getPasseport());
            passeport.setFileName(detail.getNomFichier());
            String filePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\avion-framework\\assets";
            passeport.regenerateFile(filePath);
            // modelAndView.setAttribute("passeport", passeport);
            modelAndView.setAttribute("filePath", passeport.getFileName());


            modelAndView.setAttribute("detail", detail);
            modelAndView.setAttribute("reservation", id);
            
            // modelAndView.setAttribute("succes", "Passeport importé avec succès");
        } catch (Exception e) {
            
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        
        return modelAndView; 
    }


    @Url(url = "reservation-details")
    @Get
    public ModelAndView reservationDetails(@Param(name = "id") String id){
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page","reservations/details.jsp");
        ReservationService reservationService=new ReservationService();
        try {
            Reservation reservation=reservationService.findById(Integer.parseInt(id));
            StatutService statutService=new StatutService();
            List<ReservationDetail> reservationDetails=reservationService.findAllDetails(Integer.parseInt(id));
            modelAndView.setAttribute("reservation",reservation);
            modelAndView.setAttribute("statut", statutService.getStatutById(Long.valueOf(reservation.getIdStatut()+"")).getStatut());
            modelAndView.setAttribute("details",reservationDetails);
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }


    @Url(url = "annuler-reservation")
    @Get
    public ModelAndView annulerReservation(CustomSession session,@Param(name = "id") String id){
         ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservation-list.jsp");
        ReservationService reservationService=new ReservationService();
        try {
            Long idD=(Long) session.get("id");
            // System.out.println(id);
           
           
            List<Reservation> reservations=reservationService.findAllByUtilisateur(idD.intValue());
            modelAndView.setAttribute("reservations",reservations);
             reservationService.annulerReservation(Integer.parseInt(id));
              modelAndView.setAttribute("succes", "Reservation annulee avec succes");
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }


    @Url(url = "vols-reservation-details")
    @Post
    public ModelAndView ajouterDetails(@Param(name = "idReservation") String idReservation,@Param(name = "idCategorieAge") String idCategorieAge,@Param(name = "idClasse") String idClasse,@Param(name = "nb") String nb,@Param(name = "promotion") String promotion){
        ModelAndView modelAndView=new ModelAndView("template-front.jsp");
        modelAndView.setAttribute("page", "reservations/reservation-details.jsp");
        CategorieAgeService categorieAgeService=new CategorieAgeService();
        ReservationService reservationService=new ReservationService();
        try {
            boolean promo=false;
            if(promotion!=null && promotion.equals("on")){
                promo=true;
            }
            modelAndView.setAttribute("reservation",Integer.parseInt(idReservation));
            modelAndView.setAttribute("categoriesAge", categorieAgeService.getAllCategoriesAge());
            ClasseService classeService=new ClasseService();
            modelAndView.setAttribute("classes",classeService.getAllClasses());
            reservationService.ajouterDetails(Integer.parseInt(idReservation),Integer.parseInt(idClasse),Integer.parseInt(idCategorieAge), Integer.parseInt(nb),promo);
            
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
            modelAndView.setAttribute("statuts", statutService.getAllBySource("vols"));
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
            modelAndView.setAttribute("statuts",statutService.getAllBySource("vols"));
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
 