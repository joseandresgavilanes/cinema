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

    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String movieTitle = session.getMovie().getTitle();
        String roomName = session.getRoom().getName();
        String schedule = session.getSchedule();

        panel.add(new JLabel("🎬 Movie: " + movieTitle));
        panel.add(new JLabel("🪑 Room: " + roomName));
        panel.add(new JLabel("🕓 Date & Time: " + schedule));
        panel.add(new JLabel("🔢 Seat: " + seat));
        panel.add(new JLabel("🎟 Type: " + ticketType));
        panel.add(new JLabel("💳 Payment: " + paymentMethod));
        panel.add(new JLabel("👤 Client: " + clientName + " (" + clientDoc + ")"));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return panel;
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
