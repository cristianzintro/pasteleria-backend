package com.mil.sabores.pasteleria_backend.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items_pedido")
@Schema(description = "Representa un producto específico dentro de un pedido")

public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación ManyToOne con el Pedido al que pertenece
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    // Relación ManyToOne con el Producto.
    // Usamos LAZY para que no cargue el producto completo a menos que se solicite.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_codigo")
    private Producto producto;

    @Schema(description = "Cantidad comprada de este producto", example = "2")
    private int cantidad;

    @Schema(description = "Precio unitario al momento de la compra", example = "45000")
    private int precioUnitario;

}

