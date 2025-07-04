package ecommerce.models;

import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    public void add(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        if (quantity > product.getQuantity()) throw new IllegalArgumentException("Requested " + quantity + ", available " + product.getQuantity());
        items.add(new CartItem(product, quantity));
    }
    public boolean isEmpty() { return items.isEmpty(); }
    public List<CartItem> getItems() { return items; }
} 