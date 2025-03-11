package controllers;

import com.google.gson.annotations.Until;

import annotation.Controller;
import annotation.ParamObject;
import annotation.Post;
import annotation.Url;
import dto.LoginDto;
import dto.SignupDto;
import models.Role;
import services.UtilisateurService;
import util.CustomSession;
import util.ModelAndView;

@Controller
public class UtilisateurController {
    @Url(url="login")
    @Post
    public ModelAndView login(@ParamObject(name = "user") LoginDto loginDto,CustomSession customSession) {
        ModelAndView modelAndView=new ModelAndView(null);
        UtilisateurService utilisateurService=new UtilisateurService();
        try {
            Role role=utilisateurService.login(customSession, loginDto.getEmail(), loginDto.getMdp());
            if(role.getRole().equals("admin")){
                modelAndView.setUrl("template-back.jsp");
            }
            else if(role.getRole().equals("utilisateur")){  
                modelAndView.setUrl("template-front.jsp");
            }
        } catch (Exception e) {
            modelAndView.setUrl("index.jsp");
            modelAndView.setAttribute("erreur", e.getMessage());
        }
        
        return modelAndView;
    }

    @Url(url="register")
    @Post
    public ModelAndView register(@ParamObject(name = "user") SignupDto signupDto){
        ModelAndView modelAndView=new ModelAndView(null);
        return modelAndView;
    }
}
