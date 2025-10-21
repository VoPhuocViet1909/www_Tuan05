package iuh.fit.vophuocviet_22730761_springboot_shopping.reposities;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Tìm theo tên sản phẩm
    List<Product> findByNameContainingIgnoreCase(String name);

    // Lọc sản phẩm trong kho
    List<Product> findByInStockTrue();

    // Lọc sản phẩm theo khoảng giá
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
}
