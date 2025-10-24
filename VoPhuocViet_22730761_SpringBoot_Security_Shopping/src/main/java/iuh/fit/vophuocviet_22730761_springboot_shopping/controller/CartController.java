package iuh.fit.vophuocviet_22730761_springboot_shopping.controller;

import iuh.fit.vophuocviet_22730761_springboot_shopping.model.Cart;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@SessionAttributes("cart")
public class CartController {

    private final ProductService productService;

    @ModelAttribute("cart")
    public Cart createCart() {
        return new Cart(new ArrayList<>());
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId,
                            @ModelAttribute("cart") Cart cart, Model model){
        Product p = productService.findById(productId);
        if (p != null) {
            cart.addProduct(p);
        }
        model.addAttribute("cart", cart);
        // quay lại trang sản phẩm (nếu muốn redirect đến product detail, có thể truyền id)
        return "redirect:/product";
    }

    @GetMapping("/view")
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("items", cart.getItems());
        model.addAttribute("total", cart.getTotalProduct());
        model.addAttribute("totalQuantity", cart.getTotalQuantity());
        return "cart/view-cart";
    }

    @GetMapping("/increase/{id}")
    public String increase(@PathVariable("id") int productId,
                           @ModelAttribute("cart") Cart cart) {
        cart.increase(productId);
        return "redirect:/cart/view";
    }

    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable("id") int productId,
                           @ModelAttribute("cart") Cart cart) {
        cart.decrease(productId);
        return "redirect:/cart/view";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") int productId,
                         @ModelAttribute("cart") Cart cart) {
        cart.removeProduct(productId);
        return "redirect:/cart/view";
    }

    @PostMapping("/clear")
    public String clear(@ModelAttribute("cart") Cart cart) {
        cart.clear();
        return "redirect:/cart/view";
    }
}