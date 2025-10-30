package com.mil.sabores.pasteleria_backend.model;


import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

    @Schema(description = "Detalles de los productos comprados (ej: 2xTC001, 1xTT002)")
    @Column(columnDefinition = "TEXT") // Permite guardar texto largo
    private String detalles;

    // NOTA: Para un proyecto real, necesitaríamos una relación @OneToMany con una clase ItemPedido.
}
