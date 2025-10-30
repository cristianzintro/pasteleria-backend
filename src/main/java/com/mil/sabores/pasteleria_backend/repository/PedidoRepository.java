package com.mil.sabores.pasteleria_backend.repository;

import com.mil.sabores.pasteleria_backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Spring Data JPA ya proporciona todos los métodos CRUD (save, find, delete).
}
