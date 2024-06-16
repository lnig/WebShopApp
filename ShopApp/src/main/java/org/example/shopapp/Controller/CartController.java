package org.example.shopapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
    @GetMapping("/cart")
    public ModelAndView showCart(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart");
        return modelAndView;
    }
}
