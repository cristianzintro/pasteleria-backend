package com.mil.sabores.pasteleria_backend.repository;

import com.mil.sabores.pasteleria_backend.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    // Métodos para buscar todos los ítems de un pedido específico, si fuera necesario
    // List<ItemPedido> findByPedidoId(Long pedidoId);
}