package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TicketViews {
    private JPanel panel1;
    private JLabel cinemaLogo;
    private JLabel roomNumber;
    private JLabel seatNumber;
    private JLabel hourActual;
    private JLabel dateActual;
    private JLabel priceTicket;
    private JLabel movieName;

    public TicketViews() {
        panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel1.setLayout(new GridLayout(6, 1)); // o cualquier layout que prefieras

        cinemaLogo = new JLabel(); // Si usas logo, añade aquí
        movieName = new JLabel();
        roomNumber = new JLabel();
        seatNumber = new JLabel();
        hourActual = new JLabel();
        dateActual = new JLabel();
        priceTicket = new JLabel();

        // Añadir componentes al panel
        panel1.add(movieName);
        panel1.add(roomNumber);
        panel1.add(seatNumber);
        panel1.add(hourActual);
        panel1.add(dateActual);
        panel1.add(priceTicket);
    }


    public void setTicketInfo(String movie, String room, String seat, String time, String date, String price) {
        movieName.setText(movie);
        roomNumber.setText("Room: " + room);
        seatNumber.setText("Seat: " + seat);
        hourActual.setText("Time: " + time);
        dateActual.setText("Date: " + date);
        priceTicket.setText("Price: " + price);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
