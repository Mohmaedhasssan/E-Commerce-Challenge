package ecommerce.tests;

import ecommerce.models.*;
import ecommerce.factories.ProductFactory;
import ecommerce.services.CheckoutService;

import java.time.LocalDate;

public class EcommerceTests {

    // 1. Basic successful checkout
    public static void testBasicCheckout() {
        System.out.println("TEST 1: Basic Checkout");
        Product cheese = ProductFactory.createShippableExpirable("Cheese", 100, 2, 0.2, LocalDate.now().plusDays(7));
        Product biscuits = ProductFactory.createShippableExpirable("Biscuits", 150, 1, 0.7, LocalDate.now().plusDays(30));
        Product card = ProductFactory.createSimple("ScratchCard", 50, 1);
        
        Customer customer = new Customer("Ali", 500);
        Cart cart = new Cart();

        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(card, 1);
            CheckoutService.checkout(customer, cart);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("FAILED: " + e.getMessage());
        }

        System.out.println();
    }

    // 2. Expired product in cart
    public static void testExpiredProduct() {
        System.out.println("TEST 2: Expired Product");
        Product expiredMilk = ProductFactory.createShippableExpirable("Milk", 80, 1, 0.5, LocalDate.now().minusDays(1));
        Customer customer = new Customer("Mona", 500);
        Cart cart = new Cart();

        try {
            cart.add(expiredMilk, 1);
            CheckoutService.checkout(customer, cart);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }

        System.out.println();
    }

    // 3. Add quantity more than available
    public static void testOutOfStock() {
        System.out.println("TEST 3: Out of Stock");
        Product tv = ProductFactory.createShippable("TV", 300, 1, 5.0);
        Customer customer = new Customer("Omar", 1000);
        Cart cart = new Cart();

        try {
            cart.add(tv, 2); // only 1 in stock
            CheckoutService.checkout(customer, cart);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }

        System.out.println();
    }

    // 4. Insufficient customer balance
    public static void testInsufficientBalance() {
        System.out.println("TEST 4: Insufficient Balance");
        Product tv = ProductFactory.createShippable("TV", 900, 1, 5.0);
        Customer customer = new Customer("Salma", 800); // not enough for TV + shipping
        Cart cart = new Cart();

        try {
            cart.add(tv, 1);
            CheckoutService.checkout(customer, cart);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }

        System.out.println();
    }

    // 5. Empty cart
    public static void testEmptyCart() {
        System.out.println("TEST 5: Empty Cart");
        Customer customer = new Customer("Nada", 1000);
        Cart cart = new Cart();

        try {
            CheckoutService.checkout(customer, cart);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }

        System.out.println();
    }
}
