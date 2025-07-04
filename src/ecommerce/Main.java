package ecommerce;

import java.time.LocalDate;
import ecommerce.models.*;
import ecommerce.factories.ProductFactory;
import ecommerce.services.CheckoutService;

public class Main {
    public static void main(String[] args) {
        Product cheese = ProductFactory.createShippableExpirable("Cheese", 100, 2, 0.2, LocalDate.now().plusDays(7));
        Product biscuits = ProductFactory.createShippableExpirable("Biscuits", 150, 1, 0.7, LocalDate.now().plusDays(30));
        Product scratchCard = ProductFactory.createSimple("ScratchCard", 50, 1);

        Customer customer = new Customer("Ali", 1000);
        Cart cart = new Cart();

        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
} 