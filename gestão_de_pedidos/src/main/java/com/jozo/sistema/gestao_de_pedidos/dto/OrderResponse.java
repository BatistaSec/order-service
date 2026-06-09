package com.jozo.sistema.gestao_de_pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        String customerName,
        BigDecimal total,
        LocalDateTime createdAt
) {
}
