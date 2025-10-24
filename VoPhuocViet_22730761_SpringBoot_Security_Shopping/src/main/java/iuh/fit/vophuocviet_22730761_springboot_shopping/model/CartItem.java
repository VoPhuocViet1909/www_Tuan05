package iuh.fit.vophuocviet_22730761_springboot_shopping.model;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Product product;
    private int quantity;

    /**
     * Trả về subtotal = product.price * quantity (BigDecimal an toàn)
     */
    public BigDecimal getSubTotalProduct() {
        if (product == null || product.getPrice() == null) {
            return BigDecimal.ZERO;
        }
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}