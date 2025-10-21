package iuh.fit.vophuocviet_22730761_springboot_shopping.config;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Category;
import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import iuh.fit.vophuocviet_22730761_springboot_shopping.reposities.CategoryRepository;
import iuh.fit.vophuocviet_22730761_springboot_shopping.reposities.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (categoryRepository.count() == 0) {
            Category laptops = categoryRepository.save(Category.builder().name("Laptops").build());
            Category phones = categoryRepository.save(Category.builder().name("Phones").build());
            Category acc = categoryRepository.save(Category.builder().name("Accessories").build());

            productRepository.saveAll(List.of(
                    Product.builder().name("Dell XPS 13").price(new BigDecimal("25000.00")).inStock(true).category(laptops).build(),
                    Product.builder().name("iPhone 15 Pro Max").price(new BigDecimal("32999.00")).inStock(true).category(phones).build(),
                    Product.builder().name("Sony WH-1000XM5").price(new BigDecimal("7990.00")).inStock(true).category(acc).build()
            ));
        }
    }
}