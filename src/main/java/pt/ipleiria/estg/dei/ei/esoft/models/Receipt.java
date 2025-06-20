package pt.ipleiria.estg.dei.ei.esoft.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Receipt {
    private int receiptNumber;
    private Date dateTime;
    private String clientName;
    private String clientDocument;
    private final List<ReceiptItem> items = new ArrayList<>();

    public Receipt(int receiptNumber, Date dateTime, String clientName, String clientDocument) {
        this.receiptNumber = receiptNumber;
        this.dateTime = dateTime;
        this.clientName = clientName;
        this.clientDocument = clientDocument;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientDocument() {
        return clientDocument;
    }

    public void addItem(ReceiptItem item) {
        items.add(item);
    }

    public List<ReceiptItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
