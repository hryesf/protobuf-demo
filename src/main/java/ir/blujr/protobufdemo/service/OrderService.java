package ir.blujr.protobufdemo.service;

import ir.blujr.protobufdemo.model.Order;
import ir.blujr.protobufdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
