package controllers;

import annotation.Controller;
import annotation.Get;
import annotation.Param;
import annotation.ParamObject;
import annotation.Post;
import annotation.Url;
import dto.AvionDto;
import models.Avion;
import services.AvionService;
import util.ModelAndView;

@Controller
public class AvionController {
    AvionService avionService=new AvionService();

    @Url(url = "avions")
    @Get
    public ModelAndView findAllAvions() {
        ModelAndView modelAndView=new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page","avions/avions.jsp");
        try {
            
        modelAndView.setAttribute("avions",avionService.getAllAvions());
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;

    
    }


    @Url(url = "avions/delete")
    @Get
    public ModelAndView delete(@Param(name = "id") String id){
        ModelAndView modelAndView=new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page","avions/avions.jsp");
        try {
            avionService.deleteAvion(Integer.parseInt(id));
            modelAndView.setUrl("redirect:avions");
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }


    @Url(url = "avions/ajouter")
    @Post
    public ModelAndView add(@ParamObject(name = "avion") AvionDto avionDto){
        ModelAndView modelAndView=new ModelAndView("template-back.jsp");
        modelAndView.setAttribute("page","avions/ajout.jsp");
        try {
            Avion avion=new Avion();
            avion.setCapacite(Integer.parseInt(avionDto.getCapacite()));
            avion.setModele(avionDto.getModele());
            avionService.ajouterAvion(avion);
            modelAndView.setUrl("redirect:avions");
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        return modelAndView;
    }
}
