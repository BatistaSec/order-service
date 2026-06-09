package com.jozo.sistema.gestao_de_pedidos.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record OrderCreatedEvent(
        Long orderId,
        String customerName,
        java.time.LocalDateTime createdAt, BigDecimal total
) implements Serializable {
}
