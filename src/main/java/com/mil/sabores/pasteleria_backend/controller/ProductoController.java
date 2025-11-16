package com.mil.sabores.pasteleria_backend.controller;

import com.mil.sabores.pasteleria_backend.model.Producto;
import com.mil.sabores.pasteleria_backend.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // opcional: permite React / Android
@Tag(name = "API de Productos", description = "Operaciones sobre productos de la pastelería")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // =================== GET: todos ===================
    @GetMapping
    public List<Producto> getAll() {
        return service.listarTodos();
    }

    // =================== GET: por id ===================
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =================== POST: crear ===================
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        if (service.existePorId(producto.getId())) {
            // ya existe un producto con ese id
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Producto creado = service.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // =================== PUT: actualizar ===================
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable int id,
            @RequestBody Producto producto) {

        if (!service.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        // aseguramos que se actualice el id correcto
        producto.setId(id);
        Producto actualizado = service.guardar(producto);
        return ResponseEntity.ok(actualizado);
    }

    // =================== DELETE: eliminar ===================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (!service.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        service.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
