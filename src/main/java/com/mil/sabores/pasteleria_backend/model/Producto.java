package com.mil.sabores.pasteleria_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor; // 1. Constructor con todos los argumentos
import lombok.Data;             // 2. Getters, Setters, toString, equals, hashCode
import lombok.NoArgsConstructor;  // 3. Constructor vacío

@Data // <-- La magia de Lombok: reemplaza getters, setters, etc.
@NoArgsConstructor // <-- Constructor vacío para JPA
@AllArgsConstructor // <-- Constructor con todos los campos
@Entity
@Table(name = "productos")
@Schema(description = "Representa un producto de la pastelería")
public class Producto {

    @Id
    @Schema(description = "Código único del producto (ej: TC001)", example = "TC001")
    private String codigo;

    @Schema(description = "Nombre del producto", example = "Torta Cuadrada de Chocolate")
    private String nombre;

    @Schema(description = "Categoría a la que pertenece el producto", example = "Tortas Cuadradas")
    private String categoria;

    @Schema(description = "Precio del producto en CLP", example = "45000")
    private int precio;

    @Schema(description = "Descripción detallada del producto", example = "Deliciosa torta de chocolate...")
    private String descripcion;

    // ¡No necesitas escribir Getters, Setters ni Constructores!
    // Lombok lo hace por ti al compilar.
}