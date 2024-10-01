package com.andres.curso.springboot.app.springbootcrud.controllers;

import com.andres.curso.springboot.app.springbootcrud.dto.AgregarProductRequest;
import com.andres.curso.springboot.app.springbootcrud.entities.Carrito;
import com.andres.curso.springboot.app.springbootcrud.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="http://examen-react.s3-website.us-east-2.amazonaws.com", originPatterns = "*")
@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(){
        Carrito carrito = carritoService.crearCarrito();
        return new ResponseEntity<>(carrito, HttpStatus.CREATED);
    }

    //@PostMapping("/{carritoId}/products/{productId}")
    @PostMapping("/agregar")
    public ResponseEntity<Carrito> agregarProduct(@RequestBody AgregarProductRequest request) {

        Carrito carritoAct = carritoService.agregarProducto(request.getCarritoId(),
                request.getProductId(), request.getQuantidade());
        return ResponseEntity.ok(carritoAct);
    }

    @DeleteMapping("/{carritoId}/products/{productId}")
    public ResponseEntity<Carrito> eliminarProducto(@PathVariable Long carritoId,
                                                    @PathVariable Long productId) {

        Carrito carritoAct = carritoService.eliminarProducto(carritoId, productId);
        return ResponseEntity.ok(carritoAct);
    }

    @GetMapping("/{carritoId}")
    public ResponseEntity<?> obterCarrito(@PathVariable Long carritoId) {
        Carrito carrito = carritoService.obtenerCarritoPorId(carritoId);
        Double total = carritoService.calcularTotal(carrito);
        return ResponseEntity.ok(total);
    }
}
