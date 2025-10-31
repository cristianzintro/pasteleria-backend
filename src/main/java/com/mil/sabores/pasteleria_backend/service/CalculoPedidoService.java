package com.mil.sabores.pasteleria_backend.service;

import com.mil.sabores.pasteleria_backend.model.Pedido;

public interface CalculoPedidoService {

    /**
     * Calcula el monto total del pedido aplicando precios unitarios correctos y descuentos.
     * @param pedido El pedido a procesar.
     * @return El pedido actualizado con el total y el posible descuento aplicado.
     */
    Pedido calcularTotalYDescuentos(Pedido pedido);
}