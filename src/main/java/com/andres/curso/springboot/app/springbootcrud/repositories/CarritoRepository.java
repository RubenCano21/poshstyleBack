package com.andres.curso.springboot.app.springbootcrud.repositories;

import com.andres.curso.springboot.app.springbootcrud.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
