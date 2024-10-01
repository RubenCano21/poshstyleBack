package com.andres.curso.springboot.app.springbootcrud.services;

import com.andres.curso.springboot.app.springbootcrud.entities.Carrito;

public interface CarritoService {

    Carrito crearCarrito();

    Double calcularTotal(Carrito carrito);

    Carrito agregarProducto(Long carritoId, Long productoId, Integer quantidade);

    Carrito eliminarProducto(Long carritoId, Long productoId);

    Carrito obtenerCarritoPorId(Long carritoId);
}
