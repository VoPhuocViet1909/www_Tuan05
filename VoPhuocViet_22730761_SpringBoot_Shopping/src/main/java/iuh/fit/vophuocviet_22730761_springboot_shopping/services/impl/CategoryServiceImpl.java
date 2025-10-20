package iuh.fit.vophuocviet_22730761_springboot_shopping.services.impl;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Category;
import iuh.fit.vophuocviet_22730761_springboot_shopping.reposities.CategoryRepository;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
}