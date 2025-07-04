package ecommerce.models;

import java.time.LocalDate;
import ecommerce.interfaces.Expirable;

public class ExpirableProduct extends Product implements Expirable {
    private LocalDate expiry;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiry) {
        super(name, price, quantity);
        this.expiry = expiry;
    }

    public boolean isExpired() { return LocalDate.now().isAfter(expiry); }
} 