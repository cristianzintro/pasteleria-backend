package com.mil.sabores.pasteleria_backend.model;


import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
@Schema(description = "Representa una orden de compra o pedido de un cliente")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Fecha y hora en que se realizó el pedido")
    private LocalDateTime fechaPedido = LocalDateTime.now();

    @Schema(description = "Estado del pedido (PENDIENTE, PROCESANDO, ENTREGADO)")
    private String estado = "PENDIENTE";

    @Schema(description = "Monto total del pedido")
    private int total;

    // CAMBIO CLAVE: Relación OneToMany con ItemPedido
    // CascadeType.ALL asegura que si borramos el Pedido, se borren todos sus Items.
    // orphanRemoval=true asegura que si eliminamos un Item de la lista, se borre de la DB.
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de productos y cantidades compradas en este pedido")
    private List<ItemPedido> items = new ArrayList<>();

    // El constructor de Lombok debe actualizarse, o eliminar @AllArgsConstructor
    // y usar @NoArgsConstructor junto con los setters. Por ahora, asumiremos que Lombok
    // funciona y usaremos solo los setters.
}
