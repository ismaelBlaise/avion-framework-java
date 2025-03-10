package controllers;

import com.google.gson.annotations.Until;

import annotation.Controller;
import annotation.ParamObject;
import annotation.Post;
import annotation.Url;
import dto.LoginDto;
import services.UtilisateurService;
import util.CustomSession;
import util.ModelAndView;

@Controller
public class UtilisateurController {
    @Url(url="/login")
    @Post
    public ModelAndView login(@ParamObject(name = "user") LoginDto loginDto,CustomSession customSession) {
        ModelAndView modelAndView=new ModelAndView(null);
        UtilisateurService utilisateurService=new UtilisateurService();
        try {
            utilisateurService.login(customSession, loginDto.getEmail(), loginDto.getMdp());
            modelAndView.setUrl("index.jsp");
        } catch (Exception e) {
            modelAndView.setUrl("index.jsp");
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        
        return modelAndView;
    }
}
