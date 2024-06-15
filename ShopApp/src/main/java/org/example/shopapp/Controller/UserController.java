package org.example.shopapp.Controller;

import org.example.shopapp.Model.DTO.User;
import org.example.shopapp.Model.Data.Client;
import org.springframework.ui.Model;
import org.example.shopapp.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping("/")
    public ModelAndView StartPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registerPage");
        model.addAttribute("user", new User());
        return modelAndView;
    }

//    @PostMapping("/register")
//    public ModelAndView register(@ModelAttribute("user") User user){
//
//        Client client = usersService.mapToClient(user);
//
//
//    }


}
