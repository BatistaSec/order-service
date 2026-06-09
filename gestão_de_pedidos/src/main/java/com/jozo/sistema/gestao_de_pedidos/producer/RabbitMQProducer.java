package com.jozo.sistema.gestao_de_pedidos.producer;

import com.jozo.sistema.gestao_de_pedidos.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;
    public void publish(OrderCreatedEvent event){

        rabbitTemplate.convertAndSend(
            "order.exchange",
                "order.created",
                event);
    }
}
