package com.andres.curso.springboot.app.springbootcrud.dto;

import lombok.Getter;

@Getter
public class AgregarProductRequest {
    // Getters y Setters
    private Long carritoId;
    private Long productId;
    private Integer quantidade;

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
