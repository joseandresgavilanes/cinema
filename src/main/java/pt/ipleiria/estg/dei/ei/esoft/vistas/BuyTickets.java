package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuyTickets extends JFrame {
    private JPanel buyTicketsPanel;
    private JLabel buyTicket;
    private JLabel session;
    private JLabel date;
    private JLabel schedule;
    private JComboBox scheduleAvailable;
    private JLabel room;
    private JLabel seat;
    private JPanel seatSelection;
    private JLabel priceTag;
    private JLabel price;
    private JLabel payment;
    private JComboBox paymentMethodOptions;
    private JLabel clientName;
    private JFormattedTextField clientNameInput;
    private JLabel dateSession;
    private JLabel roomSession;
    private JButton addProduct;
    private JLabel clientDocumentIdentification;
    private JFormattedTextField clientDocIdentifInput;
    private JButton buyNow;
    private JLabel movie;
    private JLabel movieName;
    private JComboBox ticketTypeCombo;
    private JLabel ticketType;

    private Session sessionSelected;

    private static final int ROWS = 5;
    private static final int COLUMNS = 5;
    private final List<JToggleButton> seatButtons = new ArrayList<>();

    // Para generar un número de recibo simple
    private static int receiptCounter = 1;

    public BuyTickets(Session session) {
        this.sessionSelected = session;
        setTitle("Buy Tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(buyTicketsPanel);

        // Inicializa UI con la sesión y sus asientos
        loadSession(session);

        // Rellenar tipos de ticket
        for (TicketType type : TicketType.values()) {
            ticketTypeCombo.addItem(type);
        }
        ticketTypeCombo.setSelectedIndex(0);
        updatePriceLabel();
        ticketTypeCombo.addActionListener(e -> updatePriceLabel());

        setVisible(true);

        addProduct.addActionListener(e -> {
            BuyProduct buyProduct = new BuyProduct();
            JFrame productFrame = new JFrame("Buy Product");
            productFrame.setContentPane(buyProduct.getPanel());
            productFrame.pack();
            productFrame.setLocationRelativeTo(null);
            productFrame.setVisible(true);
        });

        buyNow.addActionListener(e -> {
            if (!SessionManager.isLoggedIn()) {
                JOptionPane.showMessageDialog(
                        this,
                        "You have to login to buy tickets.",
                        "Access denied",
                        JOptionPane.WARNING_MESSAGE
                );

                Login loginWindow = new Login();
                loginWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                loginWindow.setVisible(true);
                return;
            }

            String clientName = clientNameInput.getText().trim();
            String clientDoc = clientDocIdentifInput.getText().trim();
            String paymentMethod = (String) paymentMethodOptions.getSelectedItem();

            boolean seatSelected = seatButtons.stream().anyMatch(JToggleButton::isSelected);

            if (clientName.isEmpty() || clientDoc.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please, fill all the fields.",
                        "Empty fields",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!seatSelected) {
                JOptionPane.showMessageDialog(
                        this,
                        "You have to choose at least one seat.",
                        "No seat selected",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (paymentMethod == null || paymentMethod.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "You have to choose a payment method.",
                        "No payment method selected",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            TicketType selectedType = (TicketType) ticketTypeCombo.getSelectedItem();

            // Crear recibo con contador y fecha actual
            Receipt receipt = new Receipt(receiptCounter++, new Date(), clientName, clientDoc);

            // Agregar tickets e items al recibo
            for (JToggleButton btn : seatButtons) {
                if (btn.isSelected()) {
                    String seatLabel = btn.getText();

                    if (TicketManager.isSeatTaken(sessionSelected, seatLabel)) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Seat " + seatLabel + " is already taken.",
                                "Seat Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        continue;
                    }

                    Ticket ticket = new Ticket(clientName, clientDoc, paymentMethod, seatLabel, selectedType, sessionSelected);
                    TicketManager.addTicket(ticket);

                    ReceiptItem item = new ReceiptItem(
                            seatLabel,
                            1,
                            "Ticket " + seatLabel + " - " + selectedType.toString(),
                            BigDecimal.valueOf(sessionSelected.calculatePrice(selectedType))
                    );
                    receipt.addItem(item);

                    btn.setEnabled(false);
                    btn.setSelected(false);
                    btn.setBackground(UIManager.getColor("Button.background"));
                }
            }

            // Asociar recibo a usuario logueado
            User currentUser = SessionManager.getCurrentUser();
            if (currentUser != null) {
                currentUser.addReceipt(receipt);
            }

            DataStore.getInstance().addReceipt(receipt);

            clientNameInput.setText("");
            clientDocIdentifInput.setText("");
            updatePriceLabel();

            // Cerrar ventana de compra
            dispose();
        });
    }

    public void loadSession(Session newSession) {
        this.sessionSelected = newSession;

        roomSession.setText(sessionSelected.getRoom().getName());
        dateSession.setText(sessionSelected.getSchedule());
        movieName.setText(sessionSelected.getMovie().getTitle());

        seatSelection.removeAll();
        seatButtons.clear();

        initializeSeatSelector();

        updatePriceLabel();

        seatSelection.revalidate();
        seatSelection.repaint();
    }

    private void initializeSeatSelector() {
        seatSelection.setLayout(new GridLayout(ROWS, COLUMNS, 2, 2));
        seatSelection.setPreferredSize(new Dimension(250, 250));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                String seatLabel = (char) ('A' + row) + String.valueOf(col + 1);
                JToggleButton seatButton = new JToggleButton(seatLabel);
                seatButton.setPreferredSize(new Dimension(5, 2));

                seatButton.addActionListener(e -> {
                    if (seatButton.isSelected()) {
                        seatButton.setBackground(Color.GREEN);
                    } else {
                        seatButton.setBackground(null);
                    }
                    updatePriceLabel();
                });

                if (TicketManager.isSeatTaken(sessionSelected, seatLabel)) {
                    seatButton.setEnabled(false);
                }

                seatButtons.add(seatButton);
                seatSelection.add(seatButton);
            }
        }
    }

    private void updatePriceLabel() {
        Object selected = ticketTypeCombo.getSelectedItem();
        if (selected instanceof TicketType) {
            TicketType selectedType = (TicketType) selected;

            long selectedSeatsCount = seatButtons.stream()
                    .filter(AbstractButton::isSelected)
                    .count();

            if (selectedSeatsCount == 0) {
                selectedSeatsCount = 1;
            }

            double unitPrice = sessionSelected.calculatePrice(selectedType);
            double totalPrice = unitPrice * selectedSeatsCount;

            priceTag.setText(String.format("%.2f €", totalPrice));
        }
    }
}
