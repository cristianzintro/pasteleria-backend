package com.mil.sabores.pasteleria_backend.service.Implementacion;


import com.mil.sabores.pasteleria_backend.model.ItemPedido;
import com.mil.sabores.pasteleria_backend.model.Pedido;
import com.mil.sabores.pasteleria_backend.model.Producto;
import com.mil.sabores.pasteleria_backend.repository.ProductoRepository;
import com.mil.sabores.pasteleria_backend.service.CalculoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CalculoPedidoServiceImpl implements CalculoPedidoService {

    @Autowired
    private ProductoRepository productoRepository;

    private static final int DESCUENTO_TORTA_GRANDE = 5; // 5% de descuento por compra grande (ej. 3 tortas)

    @Override
    public Pedido calcularTotalYDescuentos(Pedido pedido) {

        int subtotal = 0;
        int totalItems = 0;

        // 1. Validar precios unitarios y calcular el subtotal
        for (ItemPedido item : pedido.getItems()) {

            // Obtener el precio actual del producto (previene manipulación de precios)
            Producto producto = productoRepository.findById(item.getProducto().getCodigo())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Producto no encontrado: " + item.getProducto().getCodigo()));

            // Asignar el precio real y la relación bidireccional
            item.setPrecioUnitario(producto.getPrecio());
            item.setPedido(pedido);

            subtotal += item.getCantidad() * item.getPrecioUnitario();
            totalItems += item.getCantidad();
        }

        int descuento = 0;

        // 2. Aplicar Regla de Negocio: Descuento por Volumen
        // Si el cliente compra 3 o más productos grandes (ej. tortas), aplica un descuento
        if (totalItems >= 3) {
            // Se calcula el descuento sobre el subtotal
            descuento = (subtotal * DESCUENTO_TORTA_GRANDE) / 100;
        }

        int totalFinal = subtotal - descuento;

        // 3. Asignar resultados al pedido
        pedido.setTotal(totalFinal);

        // NOTA: Para un proyecto real, agregarías un campo 'descuentoAplicado' al modelo Pedido
        // para registrar el monto descontado.

        return pedido;
    }
}