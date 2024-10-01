package com.andres.curso.springboot.app.springbootcrud.services;

import com.andres.curso.springboot.app.springbootcrud.entities.Carrito;
import com.andres.curso.springboot.app.springbootcrud.entities.Product;
import com.andres.curso.springboot.app.springbootcrud.repositories.CarritoRepository;
import com.andres.curso.springboot.app.springbootcrud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoServiceImpl implements CarritoService {


    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public CarritoServiceImpl(CarritoRepository carritoRepository, ProductRepository productRepository) {
        this.carritoRepository = carritoRepository;
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public Carrito crearCarrito() {
        Carrito carrito = new Carrito();
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public Carrito agregarProducto(Long carritoId, Long productoId, Integer quantidade) {

        Product product = productRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Carrito carrito;
        if (carritoId != null) {
            carrito = carritoRepository.findById(carritoId)
                    .orElse(new Carrito()); //si no existe, crea un nuevo carrito
        } else {
            carrito = new Carrito(); // crea uno nuevo si no se proporciona uno nuevo
        }

        //verificar si el producto ya esta en el carrito
        if (carrito.getProducts().contains(product)) {
            //si ya existe actualiza la cantidad
            for (Product p : carrito.getProducts()) {
                if (p.getId().equals(productoId)) {
                    p.setQuantity(p.getQuantity() + quantidade);
                    break;
                }
            }
        }else {
            // si no existe, agrega el producto
            product.setQuantity(quantidade);
            carrito.getProducts().add(product);
        }

        carrito.setQuantidade(carrito.getProducts().stream()
                .mapToInt(Product::getQuantity).sum());

        carrito.calcularTotal();
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public Carrito eliminarProducto(Long carritoId, Long productoId) {

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Product product = productRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        carrito.getProducts().remove(product);
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public Carrito obtenerCarritoPorId(Long carritoId) {
        return carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }


    public Double calcularTotal(Carrito carrito) {
        return carrito.getProducts().stream()
                .peek(p -> System.out.println("Product: " + p.getName() + ", Quantity: " + p.getQuantity()))
                .mapToDouble(p -> {
                    Integer qty = p.getQuantity();
                    return (qty != null ? p.getPrice() * qty : 0);
                })
                .sum();
    }

}
