package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Genre;
import pt.ipleiria.estg.dei.ei.esoft.models.Language;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;

import javax.swing.*;
import java.awt.*;

public class CreateMovieForm extends JFrame {
    private JPanel adminPanel;
    private JTextField tituloTextField;
    private JTextField duracionTextField;
    private JTextField sinopsisTextField;
    private JButton cancelButton;
    private JButton saveButton;
    private JComboBox<Genre> generoOptions;
    private JComboBox<Language> languageOptions;

    private final Runnable onMovieSaved;
    private final Movie editingMovie;

    /**
     * @param movieToEdit   null → crear; no-null → editar
     * @param onMovieSaved  callback para recargar tabla
     */
    public CreateMovieForm(Movie movieToEdit, Runnable onMovieSaved) {
        this.editingMovie = movieToEdit;
        this.onMovieSaved = onMovieSaved;

        adminPanel.setBackground(Color.DARK_GRAY);

        setTitle(editingMovie != null ? "Edit Movie" : "Create Movie");
        setContentPane(adminPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // carga géneros y lenguajes
        for (Genre g : Genre.values())   generoOptions.addItem(g);
        for (Language l : Language.values()) languageOptions.addItem(l);

        // si venimos a editar, rellenamos campos
        if (editingMovie != null) {
            tituloTextField.setText(editingMovie.getTitle());
            sinopsisTextField.setText(editingMovie.getDescription());
            duracionTextField.setText(String.valueOf(editingMovie.getDuration()));
            generoOptions.setSelectedItem(editingMovie.getGenre());
            languageOptions.setSelectedItem(editingMovie.getLanguage());
        }

        cancelButton.addActionListener(e -> dispose());

        saveButton.addActionListener(e -> {
            String title    = tituloTextField.getText().trim();
            String synop    = sinopsisTextField.getText().trim();
            String durText  = duracionTextField.getText().trim();
            Genre genre     = (Genre) generoOptions.getSelectedItem();
            Language lang   = (Language) languageOptions.getSelectedItem();

            if (title.isEmpty() || durText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Título y duración son obligatorios.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int duration;
            try {
                duration = Integer.parseInt(durText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "La duración debe ser un número entero.",
                        "Error de formato",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            DataStore ds = DataStore.getInstance();
            if (editingMovie != null) {
                // — MODO EDICIÓN —
                editingMovie.setTitle(title);
                editingMovie.setDescription(synop);
                editingMovie.setDuration(duration);
                editingMovie.setGenre(genre);
                editingMovie.setLanguage(lang);
                ds.updateMovie(editingMovie);

                JOptionPane.showMessageDialog(this,
                        "Película actualizada correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // — MODO CREACIÓN —
                Movie m = new Movie(title, synop, duration, genre, lang, "");
                ds.addMovie(m);

                JOptionPane.showMessageDialog(this,
                        "Película creada correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            if (onMovieSaved != null) onMovieSaved.run();
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
    }

    /** constructor de conveniencia para solo creación */
    public CreateMovieForm(Runnable onMovieCreated) {
        this(null, onMovieCreated);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new CreateMovieForm(() -> {}).setVisible(true)
        );
    }
}
