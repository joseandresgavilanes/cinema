package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Session;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowSession extends JFrame {
    private JPanel SessionPanel;
    private JPanel MovieContainer;
    private JScrollPane ScrollPanel;
    private JButton billboardButton;
    private JButton barButton;
    private JButton loginButton;
    private JLabel image;
    private JPanel Navbar;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShowSession().setVisible(true));
    }

    public ShowSession() {
        setContentPane(SessionPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImage = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(scaledImage));

        MovieContainer.setLayout(new BoxLayout(MovieContainer, BoxLayout.Y_AXIS));

        // 1) Obtengo las sesiones del singleton
        List<Session> funciones = DataStore.getInstance().getSessions();

        // 2) Creo un panel por cada sesión
        for (Session f : funciones) {
            ShowMoviePanel panel = new ShowMoviePanel();
            panel.setTitulo(      f.getMovie().getTitle()      );
            panel.setDescripcion(f.getMovie().getDescription());
            panel.setPhoto(f.getMovie().getImagePath());
            // Si tu ShowMoviePanel sigue teniendo comboBox de horarios:
            // panel.setHorarios(f.getMovie().getHorarios());

            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            MovieContainer.add(panel);
        }

        MovieContainer.revalidate();
        MovieContainer.repaint();

        //mostrar el login
        loginButton.addActionListener(e -> {
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
        });

        //mostrar el ShowProduct
        barButton.addActionListener(e -> {
            ShowProduct showWindow = new ShowProduct();
            showWindow.setVisible(true);
        });




    }

}
