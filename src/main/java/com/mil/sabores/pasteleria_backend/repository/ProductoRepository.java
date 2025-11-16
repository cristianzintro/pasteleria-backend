package com.mil.sabores.pasteleria_backend.repository;

import com.mil.sabores.pasteleria_backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
