package controllers;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

import annotation.Controller;
import annotation.Get;
import annotation.Param;
import annotation.ParamObject;
import annotation.Post;
import annotation.Url;
import dto.VolDto;
import models.Vol;
import services.VolService;
import util.ModelAndView;

@Controller
public class VolController {

    private VolService volService = new VolService();

    // Afficher tous les vols
    @Url(url = "vols")
    @Get
    public ModelAndView findAllVols() {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page", "vols/vols.jsp");
        try {
            modelAndView.setAttribute("vols", volService.getAllVols());
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }

    // Supprimer un vol
    @Url(url = "vols-delete")
    @Get
    public ModelAndView delete(@Param(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page", "vols/vols.jsp");
        try {
            volService.deleteVol(Long.parseLong(id));
            modelAndView.setUrl("redirect:vols");
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }

    // Afficher le formulaire de modification d'un vol
    @Url(url = "vols-update-form")
    @Get
    public ModelAndView updateForm(@Param(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page", "vols/modifier.jsp");
        try {
            Vol vol = volService.getVolById(Long.parseLong(id));
            modelAndView.setAttribute("vol", vol);
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }

    // Ajouter un vol
    @Url(url = "vols-ajouter")
    @Post
    public ModelAndView add(@ParamObject(name = "vol") VolDto volDto) {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page", "vols/ajout.jsp");
        try {
            Vol vol = new Vol();
            vol.setNumero(volDto.getNumero());
            vol.setDateVol(volDto.getDateVol());
            vol.setHeureDepart(volDto.getHeureDepart());
            vol.setHeureArrive(volDto.getHeureArrive());
            vol.setHeureReservation(volDto.getHeureReservation());
            vol.setHeureAnnulation(volDto.getHeureAnnulation());
            vol.setIdStatut(Long.parseLong(volDto.getIdStatut()));
            vol.setIdVilleDepart(Long.parseLong(volDto.getIdVilleDepart()));
            vol.setIdVilleArrive(Long.parseLong(volDto.getIdVilleArrive()));
            vol.setIdAvion(Long.parseLong(volDto.getIdAvion()));
            volService.ajouterVol(vol);
            modelAndView.setUrl("redirect:vols");
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }

    // Modifier un vol
    @Url(url = "vols-update")
    @Post
    public ModelAndView update(@Param(name = "id") String id,@ParamObject(name = "vol") VolDto volDto) {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page", "vols/modifier.jsp");
        try {
            Vol vol = new Vol();
            vol.setIdVol(Long.parseLong(id));
            vol.setNumero(volDto.getNumero());
            vol.setDateVol(volDto.getDateVol());
            vol.setHeureDepart(volDto.getHeureDepart());
            vol.setHeureArrive(volDto.getHeureArrive());
            vol.setHeureReservation(volDto.getHeureReservation());
            vol.setHeureAnnulation(volDto.getHeureAnnulation());
            vol.setIdStatut(Long.parseLong(volDto.getIdStatut()));
            vol.setIdVilleDepart(Long.parseLong(volDto.getIdVilleDepart()));
            vol.setIdVilleArrive(Long.parseLong(volDto.getIdVilleArrive()));
            vol.setIdAvion(Long.parseLong(volDto.getIdAvion()));
            volService.updateVol(vol);
            modelAndView.setUrl("redirect:vols");
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }

    // Formulaire d'ajout d'un vol
    @Url(url = "vols-ajout-form")
    @Get
    public ModelAndView addForm() {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page", "vols/ajout.jsp");
        return modelAndView;
    }

    @Url(url = "vols-configuration")
    @Get
    public ModelAndView configurationMenu(@Param(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("id", id);
        modelAndView.setAttribute("page", "vols/menu-configuration.jsp");
        return modelAndView;
    }

    @Url(url = "vols-heure-reservation-form")
    @Get
    public ModelAndView heureReservationForm(@Param(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page", "vols/heure-reservation.jsp");
        try {
            Vol vol = volService.getVolById(Long.parseLong(id));
            modelAndView.setAttribute("vol", vol);
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }

    @Url(url = "vols-heure-reservation")
    @Get
    public ModelAndView heureReservation(@Param(name = "id") String id, @Param(name = "heureReservation") String heureReservation) {
        ModelAndView modelAndView = new ModelAndView("redirect:vols");
        // modelAndView.setAttribute("page", "vols/heure-reservation.jsp");
        try {
            
             // Convertir en LocalTime
            // LocalTime localTime = LocalTime.parse(heureReservation);

            // Ajouter un fuseau horaire (ex: UTC+2)
            // OffsetTime offsetTime = localTime.atOffset(ZoneOffset.of("+03:00"));
            volService.ajouterHeureReservation(id, heureReservation+"+03:00");
            Vol vol = volService.getVolById(Long.parseLong(id));
            modelAndView.setAttribute("vol", vol);

        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }


    @Url(url = "vols-heure-annulation-form")
    @Get
    public ModelAndView heureAnnulationForm(@Param(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page", "vols/heure-annulation.jsp");
        try {
            Vol vol = volService.getVolById(Long.parseLong(id));
            modelAndView.setAttribute("vol", vol);
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }

    @Url(url = "vols-heure-annulation")
    @Get
    public ModelAndView heureAnnulation(@Param(name = "id") String id, @Param(name = "heureAnnulation") String heureAnnulation) {
        ModelAndView modelAndView = new ModelAndView("redirect:vols");
        // modelAndView.setAttribute("page", "vols/heure-reservation.jsp");
        try {
            // LocalTime localTime = LocalTime.parse(heureAnnulation);

            // Ajouter un fuseau horaire (ex: UTC+2)
            // OffsetTime offsetTime = localTime.atOffset(ZoneOffset.of("+03:00"));
            volService.ajouterHeureAnnulation(id, heureAnnulation+"+03:00");
            Vol vol = volService.getVolById(Long.parseLong(id));
            modelAndView.setAttribute("vol", vol);

        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }
}
