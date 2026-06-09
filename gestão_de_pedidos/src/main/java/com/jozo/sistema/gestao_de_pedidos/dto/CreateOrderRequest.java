package com.jozo.sistema.gestao_de_pedidos.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(
        String customerName,
        BigDecimal total
) {
}
