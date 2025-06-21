package pt.ipleiria.estg.dei.ei.esoft.models;

import java.math.BigDecimal;

public class ReceiptItem {
    private String productID;
    private int quantity;
    private String description;
    private BigDecimal singlePrice;

    public ReceiptItem(String productID, int quantity, String description, BigDecimal singlePrice) {
        this.productID = productID;
        this.quantity = quantity;
        this.description = description;
        this.singlePrice = singlePrice;
    }

    public String getProductID() { return productID; }
    public int getQuantity() { return quantity; }
    public String getDescription() { return description; }
    public BigDecimal getSinglePrice() { return singlePrice; }
    public BigDecimal getTotalPrice() { return singlePrice.multiply(BigDecimal.valueOf(quantity)); }
}
