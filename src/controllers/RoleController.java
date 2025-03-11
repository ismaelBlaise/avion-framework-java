package controllers;

import annotation.Controller;
import annotation.Get;
import annotation.Url;
import services.RoleService;
import util.ModelAndView;

@Controller
public class RoleController{
    @Url(url = "roles")
    @Get
    public ModelAndView signup() {
        ModelAndView modelAndView=new ModelAndView("signup.jsp");   
        try {
            
            RoleService roleService=new RoleService();
            modelAndView.setAttribute("roles", roleService.findAll());
        } catch (Exception e) {
            modelAndView.setAttribute("erreur", e.getMessage());
        }

        return modelAndView;
    }
}