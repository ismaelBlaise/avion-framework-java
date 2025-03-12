package controllers;

import annotation.Controller;
import annotation.Get;
import annotation.Url;
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
}
