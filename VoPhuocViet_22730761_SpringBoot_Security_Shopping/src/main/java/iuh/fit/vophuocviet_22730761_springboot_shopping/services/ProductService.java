package iuh.fit.vophuocviet_22730761_springboot_shopping.services;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Product save(Product product);
    void deleteById(int id);

  //  List<Product> findByNameContaining(String name);
}
