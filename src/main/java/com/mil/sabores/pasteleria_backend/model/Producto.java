package com.mil.sabores.pasteleria_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "productos")
@Schema(description = "Representa un producto de la pastelería")
public class Producto {

    @Id
    @Schema(description = "ID único del producto", example = "1")
    private int id;

    @Schema(description = "Código del producto (ej: TC001)", example = "TC001")
    private String codigo;

    @Schema(description = "Nombre del producto", example = "Torta Cuadrada de Chocolate")
    private String nombre;

    @Schema(description = "Categoría del producto", example = "Tortas Cuadradas")
    private String categoria;

    @Schema(description = "Precio del producto en CLP", example = "45000")
    private int precio;

    @Schema(description = "Descripción detallada del producto", example = "Deliciosa torta de chocolate...")
    private String descripcion;

    @Schema(description = "URL de la imagen del producto")
    private String imagen;

    // ===== Constructores =====

    public Producto() {
    }

    public Producto(int id, String codigo, String nombre, String categoria,
                    int precio, String descripcion, String imagen) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // ===== Getters y Setters =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
