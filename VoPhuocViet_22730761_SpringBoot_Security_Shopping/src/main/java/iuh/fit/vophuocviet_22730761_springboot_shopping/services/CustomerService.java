package iuh.fit.vophuocviet_22730761_springboot_shopping.services;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    // Lưu customer (thêm)
    Customer save(Customer customer);
}
