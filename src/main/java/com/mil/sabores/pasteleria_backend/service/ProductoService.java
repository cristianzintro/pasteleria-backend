package com.mil.sabores.pasteleria_backend.service;

import com.mil.sabores.pasteleria_backend.model.Producto;
import com.mil.sabores.pasteleria_backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene todos los productos.
     */
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    /**
     * Busca un producto por su ID.
     */
    public Optional<Producto> buscarPorId(int id) {
        return productoRepository.findById(id);
    }

    /**
     * Crea o actualiza un producto.
     */
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Elimina un producto por ID.
     */
    public void eliminarPorId(int id) {
        productoRepository.deleteById(id);
    }

    /**
     * Verifica si existe un producto por ID.
     */
    public boolean existePorId(int id) {
        return productoRepository.existsById(id);
    }
}
