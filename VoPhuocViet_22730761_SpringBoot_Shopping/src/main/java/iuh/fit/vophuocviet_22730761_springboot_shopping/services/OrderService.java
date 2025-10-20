package iuh.fit.vophuocviet_22730761_springboot_shopping.services;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Order;
import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(int id);
}
