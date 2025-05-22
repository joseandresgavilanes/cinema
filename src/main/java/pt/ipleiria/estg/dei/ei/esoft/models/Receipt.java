package pt.ipleiria.estg.dei.ei.esoft.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Receipt {
    private int receiptNumber;          // nº de recibo
    private Date dateTime;     // fecha/hora emisión
    private String clientName;          // nombre de cliente
    private String clientDocument;      // documento de cliente

    // Líneas del recibo (cada producto vendido)
//    private final List<ReceiptItem> items = new ArrayList<>();

    public Receipt(int receiptNumber,
                   Date dateTime,
                   String clientName,
                   String clientDocument) {
        this.receiptNumber  = receiptNumber;
        this.dateTime       = dateTime;
        this.clientName     = clientName;
        this.clientDocument = clientDocument;
    }

    public int getReceiptNumber()       { return receiptNumber; }
    public Date getDateTime()  { return dateTime; }
    public String getClientName()       { return clientName; }
    public String getClientDocument()   { return clientDocument; }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public void setClientDocument(String clientDocument) {
        this.clientDocument = clientDocument;
    }

    /** Añade una línea de venta al recibo */
//    public void addItem(ReceiptItem item) {
//        items.add(item);
//    }
//
//    /** Devuelve las líneas del recibo (solo lectura) */
//    public List<ReceiptItem> getItems() {
//        return Collections.unmodifiableList(items);
//    }
}
