package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Session;
import javax.swing.*;
import java.util.List;

public class ShowSession extends JFrame {
    private JPanel SessionPanel;
    private JPanel MovieContainer;
    private JScrollPane ScrollPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShowSession().setVisible(true));
    }

    public ShowSession() {
        setContentPane(SessionPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        MovieContainer.setLayout(new BoxLayout(MovieContainer, BoxLayout.Y_AXIS));

        // 1) Obtengo las sesiones del singleton
        List<Session> funciones = DataStore.getInstance().getSessions();

        // 2) Creo un panel por cada sesión
        for (Session f : funciones) {
            ShowMoviePanel panel = new ShowMoviePanel();
            panel.setTitulo(      f.getMovie().getTitle()      );
            panel.setDescripcion(f.getMovie().getDescription());
            // Si tu ShowMoviePanel sigue teniendo comboBox de horarios:
            // panel.setHorarios(f.getMovie().getHorarios());

            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            MovieContainer.add(panel);
        }

        MovieContainer.revalidate();
        MovieContainer.repaint();
    }

}
