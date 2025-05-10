package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.Session;
import pt.ipleiria.estg.dei.ei.esoft.Movie;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowSession extends JFrame {
    private JPanel SessionPanel;
    private JPanel MovieContainer;

    public static void main(String[] args) {
        new ShowSession().setVisible(true);
    }

    public ShowSession() {
        setContentPane(SessionPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        MovieContainer.setLayout(new BoxLayout(MovieContainer, BoxLayout.Y_AXIS));

        List<Session> funciones = crearFuncionesDePrueba();

        for (Session f : funciones) {
            ShowMoviePanel panel = new ShowMoviePanel(); // creado desde GUI Designer
            panel.setTitulo(f.getMovie().getTitulo());
            panel.setDescripcion(f.getMovie().getDescripcion());
            panel.setHorarios(f.getMovie().getHorarios());

            MovieContainer.add(panel);
        }

        MovieContainer.revalidate();
        MovieContainer.repaint();
    }

    private List<Session> crearFuncionesDePrueba() {
        List<Session> funciones = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String titulo = "Movie " + i;
            String descripcion = "Info movie " + i;
            List<String> horarios = Arrays.asList("12:00", "15:00", "18:00");
            Movie peli = new Movie(titulo, descripcion, horarios);
            funciones.add(new Session(peli));
        }
        return funciones;
    }

}
