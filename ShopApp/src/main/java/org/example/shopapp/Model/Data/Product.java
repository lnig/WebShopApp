package org.example.shopapp.Model.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private Set<Cart> carts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Orderitem> orderitems = new LinkedHashSet<>();

}