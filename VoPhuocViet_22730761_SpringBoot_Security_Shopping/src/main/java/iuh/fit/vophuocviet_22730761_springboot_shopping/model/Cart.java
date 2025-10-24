package iuh.fit.vophuocviet_22730761_springboot_shopping.model;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product p){
        if (p == null) return;
        Integer pid = p.getId();
        for (CartItem item : items){
            if (item != null && item.getProduct() != null && Objects.equals(item.getProduct().getId(), pid)) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(CartItem.builder().product(p).quantity(1).build());
    }

    public void removeProduct(int productId){
        items.removeIf(item -> item.getProduct() != null && Objects.equals(item.getProduct().getId(), productId));
    }

    public void increase(int productId) {
        updateQuantityProduct(productId, +1);
    }

    public void decrease(int productId) {
        updateQuantityProduct(productId, -1);
    }

    public void updateQuantityProduct(int productId, int quantityDelta){
        items = items.stream()
                .peek(item -> {
                    if (item.getProduct() != null && Objects.equals(item.getProduct().getId(), productId)) {
                        item.setQuantity(Math.max(0, item.getQuantity() + quantityDelta));
                    }
                })
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalProduct() {
        return items.stream()
                .map(CartItem::getSubTotalProduct)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getTotalQuantity() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public void clear(){
        items.clear();
    }
}