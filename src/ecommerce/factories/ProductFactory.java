package ecommerce.factories;

import java.time.LocalDate;
import ecommerce.models.*;

public class ProductFactory {
    public static Product createShippable(String name, double price, int quantity, double weight) {
        return new ShippableProduct(name, price, quantity, weight);
    }

    public static Product createExpirable(String name, double price, int quantity, LocalDate expiry) {
        return new ExpirableProduct(name, price, quantity, expiry);
    }

    public static Product createShippableExpirable(String name, double price, int quantity, double weight, LocalDate expiry) {
        return new ShippableExpirableProduct(name, price, quantity, weight, expiry);
    }

    public static Product createSimple(String name, double price, int quantity) {
        return new Product(name, price, quantity) {};
    }
} 