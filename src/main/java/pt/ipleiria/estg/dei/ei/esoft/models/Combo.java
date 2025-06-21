package pt.ipleiria.estg.dei.ei.esoft.models;

import java.util.List;

public class Combo {
    private String name;
    private TicketType ticketType;
    private List<Product> includedProducts;
    private double discountPercentage; // e.g. 0.10 = 10%

    public Combo(String name, TicketType ticketType, List<Product> includedProducts, double discountPercentage) {
        this.name = name;
        this.ticketType = ticketType;
        this.includedProducts = includedProducts;
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountedPrice() {
        double ticketPrice = ticketType.getBasePrice();
        double productPrice = includedProducts.stream().mapToDouble(Product::getPrice).sum();
        double total = ticketPrice + productPrice;
        return total * (1.0 - discountPercentage);
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return includedProducts;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
