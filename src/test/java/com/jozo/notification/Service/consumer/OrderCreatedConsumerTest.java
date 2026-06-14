package com.jozo.notification.Service.consumer;

import static org.junit.jupiter.api.Assertions.*;
import com.jozo.notification.Service.dto.OrderCreatedEvent;
import com.jozo.notification.Service.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderCreatedConsumerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private OrderCreatedConsumer consumer;

    @Test
    void shouldCallNotificationServiceWhenReceiveEvent() {
        OrderCreatedEvent event = new OrderCreatedEvent(
                1l,
                "Joao silva",
                new BigDecimal("199.90"),
                LocalDateTime.now()
        );

        consumer.receive(event);
        verify(notificationService, times(1)).process(event);
    }

}