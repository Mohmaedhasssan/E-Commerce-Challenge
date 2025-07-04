package ecommerce.models;

import java.time.LocalDate;
import ecommerce.interfaces.Shippable;
import ecommerce.interfaces.Expirable;

public class ShippableExpirableProduct extends Product implements Shippable, Expirable {
    private double weight;
    private LocalDate expiry;

    public ShippableExpirableProduct(String name, double price, int quantity, double weight, LocalDate expiry) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiry = expiry;
    }

    public double getWeight() { return weight; }
    public boolean isExpired() { return LocalDate.now().isAfter(expiry); }
} 