package ecommerce.services;

import java.util.*;
import ecommerce.interfaces.Shippable;

public class ShippingService {
    public static double ship(List<Shippable> items) {
        if (items.isEmpty()) throw new IllegalArgumentException("Shippable Itmes must be > 0");

        Map<String, Integer> counts = new LinkedHashMap<>();
        Map<String, Double> weights = new HashMap<>();
        double totalWeight = calculateWeightAndGroup(items, counts, weights);

        printShipmentNotice(counts, weights, totalWeight);
        return 30; // flat fee
    }

    private static double calculateWeightAndGroup(List<Shippable> items, Map<String, Integer> counts, Map<String, Double> weights) {
        double totalWeight = 0;
        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            counts.put(name, counts.getOrDefault(name, 0) + 1);
            weights.putIfAbsent(name, weight);
            totalWeight += weight;
        }
        return totalWeight;
    }

    private static void printShipmentNotice(Map<String, Integer> counts, Map<String, Double> weights, double totalWeight) {
        System.out.println("** Shipment notice **");
        for (String name : counts.keySet()) {
            int quantity = counts.get(name);
            double weightPerItem = weights.get(name);
            System.out.printf("%dx %s %.0fg%n", quantity, name, quantity * weightPerItem * 1000);
        }
        System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
    }
} 