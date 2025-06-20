package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.TicketType;
import pt.ipleiria.estg.dei.ei.esoft.vistas.components.TicketSelectorComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SelectTickets extends JFrame {
    private JPanel mainPanel;
    private JPanel ticketTypePanel;
    private JButton continueButton;

    private final List<TicketSelectorComponent> ticketSelectors = new ArrayList<>();

    public SelectTickets() {
        setTitle("Select Tickets");
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        mainPanel.setBackground(Color.BLACK);
        ticketTypePanel.setLayout(new BoxLayout(ticketTypePanel, BoxLayout.Y_AXIS));
        ticketTypePanel.setBackground(Color.BLACK);

        for (TicketType type : TicketType.values()) {
            TicketSelectorComponent selector = new TicketSelectorComponent(type);
            ticketTypePanel.add(selector);
            ticketSelectors.add(selector);
        }

        continueButton.addActionListener(e -> proceedToSeatSelection());
    }

    private void proceedToSeatSelection() {
        Map<TicketType, Integer> selectedTickets = new LinkedHashMap<>();
        for (TicketSelectorComponent selector : ticketSelectors) {
            int quantity = selector.getCount();
            if (quantity > 0) {
                selectedTickets.put(selector.getTicketType(), quantity);
            }
        }

        if (selectedTickets.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select at least one ticket.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            // Open the seat selection window
            SeatSelection seatSelectionWindow = new SeatSelection(selectedTickets);
            seatSelectionWindow.setVisible(true);

            // Optionally close this window
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SelectTickets().setVisible(true);
        });
    }
}
