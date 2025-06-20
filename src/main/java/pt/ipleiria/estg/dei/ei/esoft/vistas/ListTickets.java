package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.Session;
import pt.ipleiria.estg.dei.ei.esoft.models.Ticket;
import pt.ipleiria.estg.dei.ei.esoft.models.TicketManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListTickets extends JFrame {
    private JPanel panel1;
    private JLabel title;
    private JPanel listTickets;

    public ListTickets() {
        setTitle("Your Tickets");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);

        listTickets.setLayout(new BoxLayout(listTickets, BoxLayout.Y_AXIS));

        showTickets(TicketManager.getTickets());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel getPanel() {
        return panel1;
    }

    public void showTickets(List<Ticket> tickets) {
        listTickets.removeAll();

        if (tickets.isEmpty()) {
            JLabel noTicketsLabel = new JLabel("No tickets purchased yet.");
            noTicketsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            listTickets.add(noTicketsLabel);
        } else {
            for (Ticket ticket : tickets) {
                JPanel ticketPanel = new JPanel();
                ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
                ticketPanel.setBorder(BorderFactory.createTitledBorder("Ticket"));

                Session session = ticket.getSession();

                JLabel movieLabel = new JLabel("🎬 Movie: " + session.getMovie().getTitle());
                JLabel roomLabel = new JLabel("🪑 Room: " + session.getRoom().getName());
                JLabel dateLabel = new JLabel("🕓 Date & Time: " + session.getSchedule());
                JLabel seatLabel = new JLabel("🔢 Seat: " + ticket.getSeat());
                JLabel typeLabel = new JLabel("🎟 Type: " + ticket.getTicketType());
                JLabel paymentLabel = new JLabel("💳 Payment: " + ticket.getPaymentMethod());

                // Estética simple
                movieLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                roomLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                seatLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                typeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                paymentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                ticketPanel.add(movieLabel);
                ticketPanel.add(roomLabel);
                ticketPanel.add(dateLabel);
                ticketPanel.add(seatLabel);
                ticketPanel.add(typeLabel);
                ticketPanel.add(paymentLabel);

                ticketPanel.setBackground(new Color(245, 245, 245));
                ticketPanel.setMaximumSize(new Dimension(700, 150));
                ticketPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                ticketPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                ticketPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                listTickets.add(ticketPanel);
                listTickets.add(Box.createVerticalStrut(10));
            }
        }

        listTickets.revalidate();
        listTickets.repaint();
    }
}
