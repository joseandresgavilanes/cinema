package pt.ipleiria.estg.dei.ei.esoft.vistas.components;

import pt.ipleiria.estg.dei.ei.esoft.models.TicketType;

import javax.swing.*;
import java.awt.*;

public class TicketSelectorComponent extends JPanel {
    private int count = 0;
    private final JLabel countLabel;
    private final TicketType ticketType;

    public TicketSelectorComponent(TicketType ticketType) {
        this.ticketType = ticketType;

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(ticketType.toString());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel priceLabel = new JLabel(String.format("$ %.2f", ticketType.getBasePrice()));
        priceLabel.setForeground(Color.LIGHT_GRAY);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setBackground(getBackground());
        textPanel.add(nameLabel);
        textPanel.add(priceLabel);

        JButton minusBtn = new JButton("–");
        JButton plusBtn = new JButton("+");
        countLabel = new JLabel("0", SwingConstants.CENTER);

        minusBtn.addActionListener(e -> {
            if (count > 0) count--;
            updateLabel();
        });

        plusBtn.addActionListener(e -> {
            count++;
            updateLabel();
        });

        JPanel counterPanel = new JPanel(new GridLayout(1, 3));
        counterPanel.setBackground(getBackground());
        counterPanel.add(minusBtn);
        counterPanel.add(countLabel);
        countLabel.setForeground(Color.WHITE);
        counterPanel.add(plusBtn);

        add(textPanel, BorderLayout.WEST);
        add(counterPanel, BorderLayout.EAST);
    }

    private void updateLabel() {
        countLabel.setText(String.valueOf(count));
    }

    public int getCount() {
        return count;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
