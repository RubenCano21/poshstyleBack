package com.andres.curso.springboot.app.springbootcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "carrito")
@JsonIgnoreProperties({"products"})  // Ignorar la relación con Product
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "carrito_product",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    private Integer quantidade;

    private Double totalPrice;


    public void calcularTotal(){
        this.totalPrice = products.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
    }

}
