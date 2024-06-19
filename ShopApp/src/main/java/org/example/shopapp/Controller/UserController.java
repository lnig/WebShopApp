package org.example.shopapp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.shopapp.JMS.NotificationController;
import org.example.shopapp.Model.DTO.EmailDto;
import org.example.shopapp.Model.DTO.Login;
import org.example.shopapp.Model.DTO.User;
import org.example.shopapp.Model.Data.Administrator;
import org.example.shopapp.Model.Data.Client;
import org.example.shopapp.Repository.ClientRepository;
import org.example.shopapp.Service.AdministratorService;
import org.example.shopapp.Service.ClientService;
import org.example.shopapp.Service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {


    private final EmailService emailService;
    private final ClientService clientService;
    private final AdministratorService administratorService;
    @Autowired
    public UserController(ClientService clientService, AdministratorService administratorService, EmailService emailService) {
        this.clientService = clientService;
        this.administratorService = administratorService;
        this.emailService = emailService;
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

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registerPage");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user){
        Client client = clientService.getClientByEmail(user.getEmail());

        if(client == null){
            Client newClient = clientService.mapUserToClient(user);
            clientService.saveClient(newClient);
            return "redirect:/login";
        }
        return "redirect:/register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test(RedirectAttributes redirectAttributes) {
        String to = "lakot54993@cnurbano.com";
        String subject = "nie";
        String text = "tak";

        // Wywołanie serwisu do wysłania emaila
        emailService.sendEmail(to, subject, text);

        // Przekierowanie po wykonaniu operacji
        return "redirect:/";
    }

}
