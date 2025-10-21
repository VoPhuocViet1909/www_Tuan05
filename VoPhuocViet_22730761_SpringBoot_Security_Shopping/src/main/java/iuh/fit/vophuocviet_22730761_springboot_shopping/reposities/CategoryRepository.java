package iuh.fit.vophuocviet_22730761_springboot_shopping.reposities;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}