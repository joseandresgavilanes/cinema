package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.TicketType;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class SeatSelection extends JFrame {
    // Grid dimensions (can be modified later for dynamic layout)
    private static final int ROWS = 5;
    private static final int COLS = 7;

    private final Set<JToggleButton> selectedSeats = new HashSet<>();
    private final int maxSeats;

    private final Map<TicketType, Integer> selectedTickets;

    public SeatSelection(Map<TicketType, Integer> selectedTickets) {
        this.selectedTickets = selectedTickets;
        setTitle("Select Your Seats");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        maxSeats = selectedTickets.values().stream().mapToInt(Integer::intValue).sum();

        JPanel seatPanel = new JPanel(new GridLayout(ROWS, COLS, 5, 5));
        seatPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        seatPanel.setBackground(Color.BLACK);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String seatLabel = (char) ('A' + row) + String.valueOf(col + 1);
                JToggleButton seatButton = new JToggleButton(seatLabel);
                seatButton.setBackground(Color.GRAY);
                seatButton.setForeground(Color.WHITE);
                seatButton.setFocusPainted(false);

                seatButton.addActionListener(e -> handleSeatSelection(seatButton));

                seatPanel.add(seatButton);
            }
        }

        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.addActionListener(e -> confirmSeats());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(new JScrollPane(seatPanel), BorderLayout.CENTER);
        mainPanel.add(confirmButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void handleSeatSelection(JToggleButton seatButton) {
        if (seatButton.isSelected()) {
            if (selectedSeats.size() < maxSeats) {
                selectedSeats.add(seatButton);
                seatButton.setBackground(Color.GREEN.darker());
            } else {
                seatButton.setSelected(false);
                JOptionPane.showMessageDialog(this, "You have reached the maximum number of allowed seats.");
            }
        } else {
            selectedSeats.remove(seatButton);
            seatButton.setBackground(Color.GRAY);
        }
    }

    private void confirmSeats() {
        if (selectedSeats.size() != maxSeats) {
            JOptionPane.showMessageDialog(
                    this,
                    "You must select exactly " + maxSeats + " seats.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Retrieve selected seat labels (e.g., A1, B2, etc.)
        List<String> seatList = selectedSeats.stream()
                .map(AbstractButton::getText)
                .sorted()
                .toList();

        // Open payment window
        PaymentWindow paymentWindow = new PaymentWindow(selectedTickets, seatList);
        paymentWindow.setVisible(true);

        // Close this window
        this.dispose();
    }
}
