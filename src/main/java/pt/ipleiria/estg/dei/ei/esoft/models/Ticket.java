package pt.ipleiria.estg.dei.ei.esoft.models;

import javax.swing.*;

public class Ticket {
    private String clientName;
    private String clientDoc;
    private String paymentMethod;
    private String seat;
    private TicketType ticketType;
    private Session session;

    public Ticket(String clientName, String clientDoc, String paymentMethod,
                  String seat, TicketType ticketType, Session session) {
        this.clientName = clientName;
        this.clientDoc = clientDoc;
        this.paymentMethod = paymentMethod;
        this.seat = seat;
        this.ticketType = ticketType;
        this.session = session;
    }

    public String getSeat() {
        return seat;
    }

    public Session getSession() {
        return session;
    }

    public String getClientName() { return clientName; }
    public String getClientDoc() { return clientDoc; }
    public String getPaymentMethod() { return paymentMethod; }
    public TicketType getTicketType() { return ticketType; }


}
