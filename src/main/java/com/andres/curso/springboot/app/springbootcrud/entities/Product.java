package com.andres.curso.springboot.app.springbootcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="products")
@JsonIgnoreProperties({"carritos"})  // Ignorar la relación con Carrito
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@IsRequired
    //@IsExistsDb
//    private String sku;
    
    //@IsRequired(message = "{IsRequired.product.name}")
    @Size(min=3, max=20)
    private String name;
    
    //@Min(value = 10, message = "{Min.product.price}")
    @NotNull(message = "{NotNull.product.price}")
    private Integer price;

    @NotNull(message = "Ingrese la talla")
    private String size;

    @NotNull(message = "Ingrese la Categoria")
    private String category;

    //@IsRequired
    private String description;

    private Integer quantity;


    @ManyToMany(mappedBy = "products")
    private List<Carrito> carritos;

}
