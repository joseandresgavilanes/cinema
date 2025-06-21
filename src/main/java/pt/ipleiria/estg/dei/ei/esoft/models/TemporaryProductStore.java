package pt.ipleiria.estg.dei.ei.esoft.models;

import pt.ipleiria.estg.dei.ei.esoft.models.Product;

import java.util.HashMap;
import java.util.Map;

public class TemporaryProductStore {
    private static Map<Product, Integer> selectedProducts = new HashMap<>();

    public static Map<Product, Integer> getSelectedProducts() {
        return new HashMap<>(selectedProducts);
    }

    public static void setSelectedProducts(Map<Product, Integer> products) {
        selectedProducts = new HashMap<>(products);
    }

    public static void clear() {
        selectedProducts.clear();
    }
}
