package iuh.fit.vophuocviet_22730761_springboot_shopping.services.impl;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Order;

import iuh.fit.vophuocviet_22730761_springboot_shopping.reposities.OrderRepository;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.OrderService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
