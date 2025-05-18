package pt.ipleiria.estg.dei.ei.esoft;

public class Product {
    private String name;
    private String description;
    private String category; // combo, bebida, snack
    private double price;
    private String Imagepath;

    public Product(String name,String description, String category,  double price) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
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
