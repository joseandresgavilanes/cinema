package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ticketShowClient {
    private JPanel panel1;
    private JLabel cinemaLogo;
    private JLabel roomNumber;
    private JLabel seatNumber;
    private JLabel hourActual;
    private JLabel dateActual;
    private JLabel priceTicket;
    private JLabel movieName;

    public ticketShowClient() {
        panel1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
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
