package com.jozo.notification.Service.consumer;

import com.jozo.notification.Service.dto.OrderCreatedEvent;
import com.jozo.notification.Service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {
    private final NotificationService service;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new JacksonJsonMessageConverter());

        return factory;
    }

    @RabbitListener(queues = "order.created.queue")
    public void receive(OrderCreatedEvent event){
        System.out.println("Recebido: "+event);
        service.process(event);
    }
}
