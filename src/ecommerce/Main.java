package ecommerce;

import ecommerce.tests.EcommerceTests;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running e-commerce test cases...\n");

        EcommerceTests.testBasicCheckout();
        EcommerceTests.testExpiredProduct();
        EcommerceTests.testOutOfStock();
        EcommerceTests.testInsufficientBalance();
        EcommerceTests.testEmptyCart();
    }
} 