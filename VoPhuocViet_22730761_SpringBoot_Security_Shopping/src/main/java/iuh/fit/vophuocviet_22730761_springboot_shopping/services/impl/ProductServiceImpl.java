package iuh.fit.vophuocviet_22730761_springboot_shopping.services.impl;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import iuh.fit.vophuocviet_22730761_springboot_shopping.reposities.ProductRepository;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

//    @Override
//    public List<Product> findByNameContaining(String name) {
//        if (name == null || name.isBlank()) {
//            return productRepository.findAll();
//        }
//        return productRepository.findByNameContainingIgnoreCase(name);
//    }
}
