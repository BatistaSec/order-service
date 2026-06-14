package com.jozo.notification.Service.consumer;

import com.jozo.notification.Service.dto.OrderCreatedEvent;
import com.jozo.notification.Service.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Testcontainers
public class OrderCreatedConsumerIntegrationTest {

    @Container
    static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.12-management");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
        registry.add("spring.rabbitmq.port", rabbitMQContainer::getFirstMappedPort);
        registry.add("spring.rabbitmq.username",rabbitMQContainer::getAdminUsername);
        registry.add("spring.rabbitmq.password",rabbitMQContainer::getAdminPassword);
    }
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MockitoBean
    private NotificationService notificationService;

    @Test
    void shouldReceiveMessageAndCallNotificationService() throws InterruptedException {
        OrderCreatedEvent event = new OrderCreatedEvent(
                1l,
                "Joao silva",
                new BigDecimal("199.90"),
                LocalDateTime.now()
        );
        rabbitTemplate.convertAndSend("order.exchange","order.created", event);

        verify(notificationService, timeout(5000).times(1)).process(event);
    }
}
