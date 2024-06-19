package org.example.shopapp.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.shopapp.Model.Data.Client;
import org.example.shopapp.Model.Data.Order;
import org.example.shopapp.Model.Data.Orderitem;
import org.example.shopapp.Model.Data.Product;
import org.example.shopapp.Repository.ClientRepository;
import org.example.shopapp.Repository.OrderRepository;
import org.example.shopapp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam("address") String address,
                             @RequestParam("totalPrice") BigDecimal totalPrice,
                             @RequestParam("cartItems") String cartItemsJson) {

        // For simplicity, assuming the client ID is retrieved from the session
        // In a real application, you would handle authentication and session management properly
        Client client = clientRepository.findById(1).orElseThrow(); // Replace with session client ID

        Order order = new Order();
        order.setClient(client);
        order.setAddress(address);
        order.setTotalPrice(totalPrice);
        order.setOrderDate(Instant.now());
        order = orderRepository.save(order);

        List<Orderitem> cartItems = parseCartItems(cartItemsJson, order, client);
        if (cartItems != null && !cartItems.isEmpty()) {
            orderItemRepository.saveAll(cartItems);
        } else {
            throw new IllegalArgumentException("Cart items must not be null or empty");
        }

        return "redirect:/";
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
