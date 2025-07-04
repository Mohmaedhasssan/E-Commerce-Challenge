package ecommerce.services;

import java.util.*;
import ecommerce.models.*;
import ecommerce.interfaces.*;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");

        double subtotal = 0;
        List<Shippable> shippables = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.product;
            validateItem(item);

            subtotal += product.getPrice() * item.quantity;
            if (product instanceof Shippable s)
                for (int i = 0; i < item.quantity; i++) shippables.add(s);
        }

        double shipping = ShippingService.ship(shippables);
        double total    = subtotal + shipping;
        if (customer.getBalance() < total)
            throw new IllegalStateException("Insufficient balance");

        for (CartItem item : cart.getItems()) item.product.reduceQuantity(item.quantity);
        customer.deduct(total);

        printReceipt(customer, cart, subtotal, shipping, total);
    }

    private static void validateItem(CartItem item) {
        Product product = item.product;
        if (product instanceof Expirable e && e.isExpired())
            throw new IllegalStateException(product.getName() + " is expired");
        if (item.quantity > product.getQuantity())
            throw new IllegalStateException(product.getName() + " out of stock");
    }

    private static void printReceipt(Customer customer, Cart cart, double subtotal, double shipping, double total) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems())
            System.out.printf("%dx %s %.0f%n", item.quantity, item.product.getName(), item.quantity * item.product.getPrice());
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shipping);
        System.out.printf("Amount %.0f%n", total);
        System.out.printf("%s balance left %.0f%n%n", customer.getName(), customer.getBalance());
    }
} 