package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class listTickets {
    private JPanel panel1;
    private JLabel title;
    private JPanel listTickets;

    public listTickets() {
        listTickets.setLayout(new BoxLayout(listTickets, BoxLayout.Y_AXIS));
    }

    public JPanel getPanel() {
        return panel1;
    }

    public void showTickets(List<ticketShowClient> tickets) {
        listTickets.removeAll();
        for (ticketShowClient ticket : tickets) {
            listTickets.add(ticket.getPanel());
        }
        listTickets.revalidate();
        listTickets.repaint();
    }
}
