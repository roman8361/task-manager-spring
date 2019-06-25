package ru.kravchenko.spring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Roman Kravchenko
 */

@Controller
public class IndexController  {

    @GetMapping("/main")
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        return model;
    }

    @GetMapping("/error")
    public ModelAndView errorPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        return model;
    }

    @GetMapping("/loginbusy")
    public ModelAndView loginBusy() {
        ModelAndView model = new ModelAndView();
        model.setViewName("loginbusy");
        return model;
    }

    @GetMapping("/registry")
    public ModelAndView logoutPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("registry");
        return model;
    }

    @GetMapping("/sessionNotFound")
    public ModelAndView sessionNotFound() {
        ModelAndView model = new ModelAndView();
        model.setViewName("session-not-found");
        System.out.println("sessionNotFound");
        return model;
    }

}
