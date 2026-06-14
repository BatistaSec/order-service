package com.jozo.notification.Service.service;

import com.jozo.notification.Service.dto.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void process(OrderCreatedEvent event){
        log.info("==========================");
        log.info("Pedido recebido");
        log.info("ID:{}",event.orderID());
        log.info("Cliente: {}", event.customerName());
        log.info("Valor: R$ {}", event.total());
        log.info("Email enviado com sucesso.");
        log.info("================================");
    }
}
