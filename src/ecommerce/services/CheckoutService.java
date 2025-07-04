package ecommerce.services;

import java.util.*;
import ecommerce.models.*;
import ecommerce.interfaces.*;

public class CheckoutService {
    public static void checkout(Customer cust, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");

        double subtotal = 0;
        List<Shippable> shippables = new ArrayList<>();

        for (CartItem ci : cart.getItems()) {
            Product p = ci.product;
            validateItem(ci);

            subtotal += p.getPrice() * ci.quantity;
            if (p instanceof Shippable s)
                for (int i = 0; i < ci.quantity; i++) shippables.add(s);
        }

        double shipping = ShippingService.ship(shippables);
        double total    = subtotal + shipping;
        if (cust.getBalance() < total)
            throw new IllegalStateException("Insufficient balance");

        for (CartItem ci : cart.getItems()) ci.product.reduceQuantity(ci.quantity);
        cust.deduct(total);

        printReceipt(cart, subtotal, shipping, total, cust.getBalance());
    }

    private static void validateItem(CartItem ci) {
        Product p = ci.product;
        if (p instanceof Expirable e && e.isExpired())
            throw new IllegalStateException(p.getName() + " is expired");
        if (ci.quantity > p.getQuantity())
            throw new IllegalStateException(p.getName() + " out of stock");
    }

    private static void printReceipt(Cart cart, double subtotal, double shipping, double total, double balanceLeft) {
        System.out.println("** Checkout receipt **");
        for (CartItem ci : cart.getItems())
            System.out.printf("%dx %s %.0f%n", ci.quantity, ci.product.getName(), ci.quantity * ci.product.getPrice());
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shipping);
        System.out.printf("Amount %.0f%n", total);
        System.out.printf("Customer balance left %.0f%n%n", balanceLeft);
    }
} 