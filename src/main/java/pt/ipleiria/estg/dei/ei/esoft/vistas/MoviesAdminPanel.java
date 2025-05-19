package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MoviesAdminPanel extends JFrame {
    private JPanel mainPanel;
    private JTable moviesTable;
    private JButton addMovieButton;

    public MoviesAdminPanel() {
        setTitle("Movies Administration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"Title", "Duration", "Genre", "Language", "Synopsis"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        moviesTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(moviesTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        loadMovies(tableModel);

        addMovieButton = new JButton("Add New Movie");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addMovieButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        addMovieButton.addActionListener(e -> {
            CreateMovieForm createForm = new CreateMovieForm(() -> {
                loadMovies((DefaultTableModel) moviesTable.getModel());
            });
            createForm.setVisible(true);
        });


        add(mainPanel);
    }

    private void loadMovies(DefaultTableModel model) {
        model.setRowCount(0); // Clear existing rows
        List<Movie> movies = DataStore.getInstance().getMovies();

        for (Movie movie : movies) {
            model.addRow(new Object[]{
                    movie.getTitle(),
                    movie.getDuration() + " min",
                    movie.getGenre(),
                    movie.getLanguage(),
                    movie.getDescription()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MoviesAdminPanel().setVisible(true));
    }
}
