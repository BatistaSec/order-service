package com.jozo.sistema.gestao_de_pedidos.repository;


import com.jozo.sistema.gestao_de_pedidos.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
