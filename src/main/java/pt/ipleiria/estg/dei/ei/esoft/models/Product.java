package pt.ipleiria.estg.dei.ei.esoft.models;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String category; // combo, bebida, snack
    private double price;
    private String imagePath;
    private Integer quantity;

    // Constructor nuevo (con quantity)
    public Product(String name, String description, String category, double price, String imagePath, Integer quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imagePath = imagePath;
        this.quantity = quantity;
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

    public Integer getQuantity() {return quantity;}

    public String getImagePath() {return imagePath;}


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



}
