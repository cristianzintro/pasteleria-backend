package com.mil.sabores.pasteleria_backend.controller;


import com.mil.sabores.pasteleria_backend.model.Pedido;
import com.mil.sabores.pasteleria_backend.repository.PedidoRepository;
import com.mil.sabores.pasteleria_backend.service.CalculoPedidoService; // NUEVO

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mil.sabores.pasteleria_backend.model.ItemPedido; // NUEVO
import com.mil.sabores.pasteleria_backend.model.Producto;
import com.mil.sabores.pasteleria_backend.repository.ProductoRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "API de Pedidos", description = "Gestión de órdenes de clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    // INYECTAMOS EL SERVICIO EN LUGAR DEL REPOSITORIO DE PRODUCTOS
    @Autowired
    private CalculoPedidoService calculoPedidoService; // CAMBIADO

    // ... (getAllPedidos es igual) ...

    // 2. POST: Crear un nuevo pedido con sus items
    @PostMapping
    @Operation(summary = "Crear un nuevo pedido", description = "Registra una nueva orden de compra con cálculo automático de total y descuentos.")
    @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Pedido inválido o producto no encontrado")
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {

        // 1. Usar el servicio para validar precios, calcular el total y aplicar descuentos.
        try {
            Pedido pedidoProcesado = calculoPedidoService.calcularTotalYDescuentos(pedido);

            // 2. Guardar el Pedido procesado (incluye items y total final)
            Pedido pedidoGuardado = pedidoRepository.save(pedidoProcesado);

            return new ResponseEntity<>(pedidoGuardado, HttpStatus.CREATED);

        } catch (ResponseStatusException e) {
            // Capturar errores del servicio (ej: Producto no encontrado)
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    // ... (updatePedido es igual) ...status
}