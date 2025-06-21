package pt.ipleiria.estg.dei.ei.esoft.models;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;

import java.util.*;

public class ComboEvaluator {

    public static List<Combo> findMatchingCombos(Map<TicketType, Integer> tickets, Map<Product, Integer> products) {
        List<Combo> matches = new ArrayList<>();

        List<Combo> allCombos = getAllCombos();

        for (Combo combo : allCombos) {
            TicketType comboType = combo.getTicketType();
            if (!tickets.containsKey(comboType) || tickets.get(comboType) < 1) {
                continue;
            }

            boolean allProductsIncluded = true;
            for (Product requiredProduct : combo.getProducts()) {
                boolean matched = products.keySet().stream()
                        .anyMatch(p -> p.getName().equalsIgnoreCase(requiredProduct.getName()));
                if (!matched) {
                    allProductsIncluded = false;
                    break;
                }
            }

            if (allProductsIncluded) {
                matches.add(combo);
            }
        }

        return matches;
    }

    private static List<Combo> getAllCombos() {
        // Aquí defines los combos disponibles de forma manual (o en el futuro desde base de datos)
        List<Combo> combos = new ArrayList<>();

        Product soda = findProductByName("Soda");
        Product popcorn = findProductByName("Popcorn");
        Product hotDog = findProductByName("Hot Dog");

        if (soda != null && popcorn != null) {
            combos.add(new Combo("Snack Pack", TicketType.STANDARD, Arrays.asList(soda, popcorn), 0.15));
        }

        if (hotDog != null && soda != null) {
            combos.add(new Combo("Hot Combo", TicketType.STUDENT, Arrays.asList(hotDog, soda), 0.20));
        }

        return combos;
    }

    private static Product findProductByName(String name) {
        return DataStore.getInstance().getProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
