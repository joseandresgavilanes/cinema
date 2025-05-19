package pt.ipleiria.estg.dei.ei.esoft.models;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String category; // combo, bebida, snack
    private double price;
    private String imagePath;

    public Product(String name, String description, String category, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imagePath = imagePath;
    }

    public String getPhoto() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
