package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.Session;
import pt.ipleiria.estg.dei.ei.esoft.models.SessionManager;
import pt.ipleiria.estg.dei.ei.esoft.models.TicketType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

    public BuyTickets(Session session) {
        this.sessionSelected = session;
        setTitle("Buy Tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(buyTicketsPanel);
        roomSession.setText(session.getRoom().getName());
        dateSession.setText(session.getSchedule());
        movieName.setText(session.getMovie().getTitle());
        for (TicketType type : TicketType.values()) {
            ticketTypeCombo.addItem(type);
        }

        ticketTypeCombo.setSelectedIndex(0);
        updatePriceLabel();

        ticketTypeCombo.addActionListener(e -> updatePriceLabel());

        initializeSeatSelector();

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
                        "Debe iniciar sesión para comprar tickets.",
                        "Acceso Denegado",
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
                        "Debe completar todos los campos obligatorios.",
                        "Campos Vacíos",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!seatSelected) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debe seleccionar al menos un asiento.",
                        "Asiento no seleccionado",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (paymentMethod == null || paymentMethod.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debe seleccionar un método de pago.",
                        "Método de Pago no seleccionado",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Compra realizada con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });
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
