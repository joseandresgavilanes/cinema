package pt.ipleiria.estg.dei.ei.esoft.models;

import java.util.Objects;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String category;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return Double.compare(p.price, price) == 0
                && Objects.equals(name, p.name)
                && Objects.equals(description, p.description)
                && Objects.equals(category, p.category)
                && Objects.equals(imagePath, p.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, category, price, imagePath);
    }
}
