package com.mil.sabores.pasteleria_backend.repository;

import com.mil.sabores.pasteleria_backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    // 1. Buscar por Categoría: Spring crea el query automáticamente
    List<Producto> findByCategoria(String categoria);

    // 2. Buscar por Rango de Precio: Buscar donde el precio es mayor o igual a minPrice Y menor o igual a maxPrice
    List<Producto> findByPrecioBetween(int minPrice, int maxPrice);

    // 3. NUEVO: Buscar por Nombre ignorando mayúsculas/minúsculas
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
