package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.Funcion;
import pt.ipleiria.estg.dei.ei.esoft.Pelicula;

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

        List<Funcion> funciones = crearFuncionesDePrueba();

        for (Funcion f : funciones) {
            ShowMoviePanel panel = new ShowMoviePanel(); // creado desde GUI Designer
            panel.setTitulo(f.getPelicula().getTitulo());
            panel.setDescripcion(f.getPelicula().getDescripcion());
            panel.setHorarios(f.getPelicula().getHorarios());

            MovieContainer.add(panel);
        }

        MovieContainer.revalidate();
        MovieContainer.repaint();
    }

    private List<Funcion> crearFuncionesDePrueba() {
        List<Funcion> funciones = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String titulo = "Movie " + i;
            String descripcion = "Info movie " + i;
            List<String> horarios = Arrays.asList("12:00", "15:00", "18:00");
            Pelicula peli = new Pelicula(titulo, descripcion, horarios);
            funciones.add(new Funcion(peli));
        }
        return funciones;
    }

}
