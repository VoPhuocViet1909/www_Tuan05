package iuh.fit.vophuocviet_22730761_springboot_shopping.services.impl;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Customer;
import iuh.fit.vophuocviet_22730761_springboot_shopping.reposities.CustomerRepository;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.CustomerService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
