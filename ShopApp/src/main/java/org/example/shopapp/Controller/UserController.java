package org.example.shopapp.Controller;

import org.example.shopapp.Model.DTO.Login;
import org.example.shopapp.Model.DTO.User;
import org.example.shopapp.Model.Data.Administrator;
import org.example.shopapp.Model.Data.Client;
import org.example.shopapp.Repository.ClientRepository;
import org.example.shopapp.Service.AdministratorService;
import org.example.shopapp.Service.ClientService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    private final ClientService clientService;
    private final AdministratorService administratorService;
    @Autowired
    public UserController(ClientService clientService, AdministratorService administratorService) {
        this.clientService = clientService;
        this.administratorService = administratorService;
    }

    @GetMapping("/") // landing page
    public ModelAndView StartPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("homePage");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        Login login = new Login();
        modelAndView.addObject("login", login);
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@ModelAttribute("login") Login login){
        ModelAndView modelAndView = new ModelAndView();

        Client isValidClient = clientService.getClient(login.getEmail(), login.getPassword());
        Administrator isValidAdministrator = administratorService.getAdministrator(login.getEmail(), login.getPassword());
        if(isValidAdministrator != null) System.out.println(isValidAdministrator.getEmail());
        if(isValidClient != null) System.out.println(isValidClient.getEmail());
        if (isValidClient != null || isValidAdministrator != null) {
            modelAndView.setViewName("homePage");
        } else {
            modelAndView.setViewName("loginPage");
            modelAndView.addObject("error", "Invalid email or password");
        }

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
