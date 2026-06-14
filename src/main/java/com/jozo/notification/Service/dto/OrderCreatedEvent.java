package com.jozo.notification.Service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderCreatedEvent(
        Long orderID,
        String customerName,
        BigDecimal total,
        LocalDateTime createdAt

) {
}
