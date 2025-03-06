package controllers;

import annotation.Controller;
import annotation.Get;
import annotation.Url;
import util.ModelAndView;

@Controller
public class TestController {
    @Url(url = "index")
    @Get
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("index.jsp");
        modelAndView.setAttribute("hello","ismael");
        return modelAndView;
    }

}
