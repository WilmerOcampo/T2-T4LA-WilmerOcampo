package com.ocampo.t2.controllers;

import com.ocampo.t2.entities.Producto;
import com.ocampo.t2.services.ProductoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    private ProductoServiceImpl productoService;

    @Autowired
    public ProductoController(ProductoServiceImpl productoService) {
        this.productoService = productoService;
    }

    @GetMapping("")
    public String findAllProducts(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "home";
    }

    private List<Optional<Producto>> carrito = new ArrayList<Optional<Producto>>();

    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        calcularTotalCarrito(model);
        model.addAttribute("productosEnCarrito", carrito);
        return "carrito";
    }

    @PostMapping("/agregarAlCarrito/{id}")
    public ResponseEntity<String> agregarAlCarrito(@PathVariable Integer id) {
        Optional<Producto> producto = productoService.findById(id);
        LOGGER.info("este es el objeto producto de WilmerOcampo {}", producto);
        String mensaje = "Producto: " + '"' + producto.get().getNombre() + '"' + ", añadido al carrito.";
        if (producto != null) {
            Optional<Producto> productoExistente = carrito.stream().filter(p -> p.get().getId() == id).map(Optional::get).findFirst();
            if (productoExistente.isPresent()) {
                productoExistente.get().setCantidad(productoExistente.get().getCantidad() + 1);
            } else {
                producto.get().setCantidad(1);
                carrito.add(producto);
            }
        }
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/vaciarCarrito")
    public String vaciarCarrito() {
        carrito.clear();
        return "redirect:/productos";
    }

    @GetMapping("/comprar")
    public String comprar(Model model) {
        model.addAttribute("mensajeCompra", "Compra realizada con éxito");
        System.out.println("Compra exitosa");
        carrito.clear();
        return "redirect:/productos";
    }

    @GetMapping("/totalCarrito")
    public String calcularTotalCarrito(Model model) {
        double total = 0.0;
        for (Optional<Producto> optionalProducto : carrito) {
            if (optionalProducto.isPresent()) {
                Producto producto = optionalProducto.get();
                total += producto.getPrecio() * producto.getCantidad();
            }
        }
        model.addAttribute("totalCarrito", total);
        LOGGER.info("Este es el total Wilmer Ocampo {}", total);
        return "carrito";
    }

    /*@PostMapping("/quitar/{id}")
    public String quitarProducto(@PathVariable Integer id) {// Encuentra y elimina el producto del carrito
        Optional<Optional<Producto>> productoAEliminar = carrito.stream()
                .filter(p -> p.get().getId() == id)
                .findFirst();

        if (productoAEliminar.isPresent()) {
            carrito.remove(productoAEliminar);
        }

        return "redirect:/productos/carrito"; // Redirige de nuevo a la vista del carrito}*/

}