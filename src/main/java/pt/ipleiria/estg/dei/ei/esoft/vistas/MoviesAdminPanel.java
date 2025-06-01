package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MoviesAdminPanel extends JFrame {
    private JTable moviesTable;
    private JButton addMovieButton;
    private JButton editMovieButton;
    private JButton deleteMovieButton;
    private JPanel mainPanel;

    public MoviesAdminPanel() {
        setTitle("Movies Administration");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        // — tabla —
        String[] cols = {"Title", "Duration", "Genre", "Language"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        moviesTable = new JTable(model);
        mainPanel.add(new JScrollPane(moviesTable), BorderLayout.CENTER);
        loadMovies(model);

        // — botones —
        addMovieButton    = new JButton("Add Movie");
        editMovieButton   = new JButton("Edit Selected");
        deleteMovieButton = new JButton("Delete Selected");
        JPanel btnPanel = new JPanel();
        btnPanel.add(addMovieButton);
        btnPanel.add(editMovieButton);
        btnPanel.add(deleteMovieButton);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        // — listeners —
        addMovieButton.addActionListener(e ->
                new CreateMovieForm(() -> loadMovies(model))
                        .setVisible(true)
        );

        editMovieButton.addActionListener(e -> {
            int row = moviesTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this,
                        "Please select a movie to edit.",
                        "No selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            Movie m = DataStore.getInstance().getMovies().get(row);
            new CreateMovieForm(m, () -> loadMovies(model))
                    .setVisible(true);
        });

        deleteMovieButton.addActionListener(e -> {
            int row = moviesTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this,
                        "Please select a movie to delete.",
                        "No selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this movie?",
                    "Confirm delete",
                    JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;

            Movie m = DataStore.getInstance().getMovies().get(row);
            DataStore.getInstance().removeMovie(m);
            loadMovies(model);
        });

        add(mainPanel);
    }

    private void loadMovies(DefaultTableModel model) {
        model.setRowCount(0);
        List<Movie> movies = DataStore.getInstance().getMovies();
        for (Movie m : movies) {
            model.addRow(new Object[]{
                    m.getTitle(),
                    m.getDuration(),
                    m.getGenre(),
                    m.getLanguage()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new MoviesAdminPanel().setVisible(true)
        );
    }
}
