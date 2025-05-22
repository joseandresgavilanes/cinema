package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.util.List;

public class ListTickets  extends JFrame {
    private JPanel panel1;
    private JLabel title;
    private JPanel listTickets;

    public ListTickets() {
        setTitle("Your Tickets");
        setSize(800, 600);
        setContentPane(panel1);
        listTickets.setLayout(new BoxLayout(listTickets, BoxLayout.Y_AXIS));
    }

    public JPanel getPanel() {
        return panel1;
    }

    public void showTickets(List<Ticket> tickets) {
        listTickets.removeAll();
        for (Ticket ticket : tickets) {
            listTickets.add(ticket.getPanel());
        }
        listTickets.revalidate();
        listTickets.repaint();
    }
}
