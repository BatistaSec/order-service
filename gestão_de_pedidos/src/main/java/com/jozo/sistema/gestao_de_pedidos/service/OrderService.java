package com.jozo.sistema.gestao_de_pedidos.service;

import com.jozo.sistema.gestao_de_pedidos.dto.CreateOrderRequest;
import com.jozo.sistema.gestao_de_pedidos.dto.OrderCreatedEvent;
import com.jozo.sistema.gestao_de_pedidos.dto.OrderResponse;
import com.jozo.sistema.gestao_de_pedidos.entity.Order;
import com.jozo.sistema.gestao_de_pedidos.producer.RabbitMQProducer;
import com.jozo.sistema.gestao_de_pedidos.repository.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class OrderService {
    private final OrderRepository repository;
    private final RabbitMQProducer producer;

    public OrderResponse create(CreateOrderRequest request){
        Order order = new Order();

        order.setCustomerName(request.customerName());
        order.setTotal(request.total());
        order.setCreatedAt(LocalDateTime.now());

        Order saveOrder =  repository.save(order);

        producer.publish(
                new OrderCreatedEvent(
                        saveOrder.getId(),
                        saveOrder.getCustomerName(),
                        saveOrder.getCreatedAt(),
                        saveOrder.getTotal()

                )
        );
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getTotal(),
                order.getCreatedAt()
        );
    }
    public OrderResponse findById(Long id){
        Order order = repository.findById(id)
                .orElseThrow();
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getTotal(),
                order.getCreatedAt()
        );
    }
    public List<OrderResponse> findAll(){
        return repository.findAll()
                .stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getCustomerName(),
                        order.getTotal(),
                        order.getCreatedAt()
                ))
                .toList();
    }
}
