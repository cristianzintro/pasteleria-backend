package com.mil.sabores.pasteleria_backend.controller;

import com.mil.sabores.pasteleria_backend.model.Pedido;
import com.mil.sabores.pasteleria_backend.repository.PedidoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "API de Pedidos", description = "Gestión de órdenes de clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    // 1. GET: Obtener todos los pedidos
    // Ruta: GET http://localhost:8081/api/pedidos
    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista de todas las órdenes de los clientes.")
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    // 2. POST: Crear un nuevo pedido
    // Ruta: POST http://localhost:8081/api/pedidos
    @PostMapping
    @Operation(summary = "Crear un nuevo pedido", description = "Registra una nueva orden de compra.")
    @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente")
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // 3. PUT: Actualizar el estado de un pedido (ej: de PENDIENTE a ENTREGADO)
    // Ruta: PUT http://localhost:8081/api/pedidos/{id}
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido", description = "Actualiza los detalles de una orden por ID.")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedidoDetails) {
        return pedidoRepository.findById(id)
                .map(pedidoExistente -> {
                    pedidoExistente.setEstado(pedidoDetails.getEstado());
                    pedidoExistente.setDetalles(pedidoDetails.getDetalles());
                    pedidoExistente.setTotal(pedidoDetails.getTotal());

                    Pedido pedidoActualizado = pedidoRepository.save(pedidoExistente);
                    return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}