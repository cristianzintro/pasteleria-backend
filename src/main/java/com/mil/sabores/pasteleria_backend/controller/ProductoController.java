package com.mil.sabores.pasteleria_backend.controller;

import com.mil.sabores.pasteleria_backend.model.Producto;
import com.mil.sabores.pasteleria_backend.repository.ProductoRepository; // Revisa 'Repository' con mayúscula.

// --- IMPORTS DE SPRING Y SWAGGER/OPENAPI ---
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "API de Productos", description = "Gestión de los productos de la pastelería")
@CrossOrigin(origins = "http://localhost:3000") // Permite la comunicación con tu Frontend de React
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // 1. GET: Obtener todos los productos
    // Ruta: GET http://localhost:8081/api/productos
    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos del catálogo.")
    @ApiResponse(responseCode = "200", description = "Productos encontrados")
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // 2. GET: Obtener un producto por ID
    // Ruta: GET http://localhost:8081/api/productos/{codigo}
    @GetMapping("/{codigo}")
    @Operation(summary = "Obtener un producto por su código", description = "Busca un producto específico usando su código (ej: TC001).")
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public Producto getProductoByCodigo(@PathVariable String codigo) {
        return productoRepository.findById(codigo).orElse(null);
    }

    // 3. POST: Crear un nuevo producto
    // Ruta: POST http://localhost:8081/api/productos
    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Recibe un objeto Producto y lo guarda en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de producto inválidos")
    public Producto createProducto(@RequestBody Producto producto) {
        // Usa 'save()' para insertar la nueva entidad
        return productoRepository.save(producto);
    }

    @GetMapping("/buscar/categoria")
    @Operation(summary = "Buscar por categoría", description = "Filtra productos por el nombre de la categoría (ej: Tortas Circulares).")
    @ApiResponse(responseCode = "200", description = "Productos filtrados encontrados")
    public List<Producto> findByCategoria(@RequestParam String nombre) {
        // Usamos el método creado en el repositorio
        return productoRepository.findByCategoria(nombre);
    }
    // 7. GET: Buscar productos por rango de precio
// Ruta: GET http://localhost:8081/api/productos/buscar/precio?min={minPrice}&max={maxPrice}
    @GetMapping("/buscar/precio")
    @Operation(summary = "Buscar por rango de precio", description = "Filtra productos entre un precio mínimo y uno máximo.")
    @ApiResponse(responseCode = "200", description = "Productos dentro del rango de precio encontrados")
    public List<Producto> findByPrecioBetween(
            @RequestParam int min,
            @RequestParam int max) {

        // Usamos el método creado en el repositorio
        return productoRepository.findByPrecioBetween(min, max);
    }



}