package iuh.fit.vophuocviet_22730761_springboot_shopping.services;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
}