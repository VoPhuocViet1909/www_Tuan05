package iuh.fit.vophuocviet_22730761_springboot_shopping.controller;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Category;
import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.CategoryService;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // Hiển thị danh sách, có thể lọc theo categoryId
    @GetMapping
    public String showAllProducts(@RequestParam(name = "categoryId", required = false) Integer categoryId,
                                  Model model) {
        List<Product> products = productService.findAll();

        if (categoryId != null) {
            products = products.stream()
                    .filter(p -> p.getCategory() != null && Objects.equals(p.getCategory().getId(), categoryId))
                    .collect(Collectors.toList());
            model.addAttribute("selectedCategoryId", categoryId);
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAll());
        return "product/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/add";
    }

    // Xử lý thêm mới (upload ảnh tùy chọn)
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product,
                             @RequestParam(value = "fileImage", required = false) MultipartFile file) throws IOException {
        // gán Category nếu có id
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category c = categoryService.findById(product.getCategory().getId());
            product.setCategory(c);
        } else {
            product.setCategory(null);
        }

        // upload ảnh nếu có
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + Paths.get(file.getOriginalFilename()).getFileName().toString();
            Path uploadDir = Paths.get("src/main/resources/static/uploads/");
            Files.createDirectories(uploadDir);
            file.transferTo(uploadDir.resolve(fileName));
            product.setImage(fileName);
        }

        productService.save(product);
        return "redirect:/product";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/product";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    // Xử lý cập nhật (upload ảnh mới thay thế nếu có)
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable int id,
                                @ModelAttribute("product") Product product,
                                @RequestParam(value = "fileImage", required = false) MultipartFile file) throws IOException {
        // giữ id
        product.setId(id);

        // gán Category nếu có
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category c = categoryService.findById(product.getCategory().getId());
            product.setCategory(c);
        } else {
            product.setCategory(null);
        }

        // xử lý ảnh
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + Paths.get(file.getOriginalFilename()).getFileName().toString();
            Path uploadDir = Paths.get("src/main/resources/static/uploads/");
            Files.createDirectories(uploadDir);
            file.transferTo(uploadDir.resolve(fileName));
            product.setImage(fileName);
        } else {
            // giữ ảnh cũ nếu có
            Product existing = productService.findById(id);
            if (existing != null) {
                product.setImage(existing.getImage());
            }
        }

        productService.save(product);
        return "redirect:/product";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

    // Chi tiết
    @GetMapping("/{id}")
    public String showProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/productdetail";
    }

    // helper: đường dẫn theo loại
    @GetMapping("/category/{categoryId}")
    public String showByCategory(@PathVariable Integer categoryId) {
        return "redirect:/product?categoryId=" + categoryId;
    }
}