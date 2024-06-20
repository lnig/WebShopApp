package org.example.shopapp.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.example.shopapp.Model.Data.Client;
import org.example.shopapp.Model.Data.Order;
import org.example.shopapp.Model.Data.Orderitem;
import org.example.shopapp.Model.Data.Product;
import org.example.shopapp.Repository.ClientRepository;
import org.example.shopapp.Repository.OrderRepository;
import org.example.shopapp.Repository.ProductRepository;
import org.example.shopapp.Repository.OrderItemRepository;
import org.example.shopapp.Service.ClientService;
import org.example.shopapp.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private  EmailService emailService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;

    @PostMapping("/place-order")
    public String placeOrder(//@RequestParam("address") String address,
                             @RequestParam("totalPrice") BigDecimal totalPrice,
                             @RequestParam("cartItems") String cartItemsJson) {


        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        UserDetails userDetails = (UserDetails) session.getAttribute("UserDetails");

        Client client = clientService.getClientByEmail(userDetails.getUsername());

        Order order = new Order();
        order.setClient(client);
        order.setAddress(client.getEmail());
        order.setTotalPrice(totalPrice);
        order.setOrderDate(Instant.now());
        order = orderRepository.save(order);



        List<Orderitem> cartItems = parseCartItems(cartItemsJson, order, client);
        if (cartItems != null && !cartItems.isEmpty()) {
            orderItemRepository.saveAll(cartItems);


            String to = userDetails.getUsername();
            String subject = "Zamówienie nr."+ order.getId();
            String text = "Dzień dobry "+client.getName()+",\n Twoje zamówienie jest w trakcie realizacji";

            emailService.sendEmail(to, subject, text);


        } else {
            throw new IllegalArgumentException("Cart items must not be null or empty");
        }

        return "redirect:/";
    }

    @GetMapping("/orders")
    public String viewOrdersWithProducts(Model model) {
        List<Order> orders = orderRepository.findAll();

        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/userorders")
    public String viewUserOrders(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        UserDetails userDetails = (UserDetails) session.getAttribute("UserDetails");

        Client client = clientService.getClientByEmail(userDetails.getUsername());

        List<Order> orders = orderRepository.findByClientId(client.getId());
        model.addAttribute("orders", orders);
        return "orders";
    }


    private List<Orderitem> parseCartItems(String cartItemsJson, Order order, Client client) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> items = objectMapper.readValue(cartItemsJson, new TypeReference<List<Map<String, Object>>>() {});
            List<Orderitem> orderItems = new ArrayList<>();

            for (Map<String, Object> item : items) {
                int productId = (Integer) item.get("id");
                int quantity = (Integer) item.get("quantity");
                Product product = productRepository.findById(productId).orElseThrow();

                Orderitem orderItem = new Orderitem();
                orderItem.setOrder(order);
                orderItem.setClient(client);
                orderItem.setProduct(product);
                orderItem.setQuantity(quantity);
                orderItems.add(orderItem);
            }
            return orderItems;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
