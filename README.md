# E-Commerce Console System

A console-based e-commerce system built with Java using Object-Oriented Programming principles and the Factory Pattern.

## Project Structure

```
src/
├── ecommerce/
│   ├── Main.java                    # Main application entry point
│   ├── interfaces/
│   │   ├── Shippable.java          # Interface for shippable products
│   │   └── Expirable.java          # Interface for expirable products
│   ├── models/
│   │   ├── Product.java            # Abstract base product class
│   │   ├── ShippableProduct.java   # Product that can be shipped
│   │   ├── ExpirableProduct.java   # Product with expiry date
│   │   ├── ShippableExpirableProduct.java # Product that is both shippable and expirable
│   │   ├── Customer.java           # Customer model
│   │   ├── Cart.java               # Shopping cart
│   │   └── CartItem.java           # Individual cart item
│   ├── services/
│   │   ├── ShippingService.java    # Handles shipping calculations
│   │   └── CheckoutService.java    # Handles checkout process
│   └── factories/
│       └── ProductFactory.java     # Factory for creating different product types
```

## Features

- **Product Types**: Support for different product types (shippable, expirable, both)
- **Factory Pattern**: Clean product creation using factory methods
- **Shopping Cart**: Add products with quantity validation
- **Checkout Process**: Complete checkout with shipping calculation
- **Expiry Validation**: Automatic expiry date checking
- **Shipping Calculation**: Weight-based shipping with flat fee

## How to Run

```bash
cd src
javac ecommerce/Main.java
java ecommerce.Main
```

## Sample Output

The system demonstrates:
- Creating different product types (cheese, biscuits, scratch card)
- Adding items to cart
- Processing checkout with shipping calculation
- Displaying shipment notice and receipt


## Requirements

- Java 8 or higher
- No external dependencies required 