package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Genre;
import pt.ipleiria.estg.dei.ei.esoft.models.Language;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;

import javax.swing.*;

public class CreateMovieForm extends JFrame {
    private JPanel adminPanel;
    private JTextField tituloTextField;
    private JTextField duracionTextField;
    private JTextField sinopsisTextField;
    private JButton cancelButton;
    private JButton saveButton;
    private JComboBox<Genre> generoOptions;
    private JComboBox<Language> languageOptions;

    private final Runnable onMovieCreated;

    public CreateMovieForm(Runnable onMovieCreated) {
        this.onMovieCreated = onMovieCreated;

        setContentPane(adminPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        for (Genre genre : Genre.values()) {
            generoOptions.addItem(genre);
        }
        for (Language lang : Language.values()) {
            languageOptions.addItem(lang);
        }

        cancelButton.addActionListener(e -> dispose());

        saveButton.addActionListener(e -> {
            String title = tituloTextField.getText().trim();
            String synopsis = sinopsisTextField.getText().trim();
            String durText = duracionTextField.getText().trim();
            Genre genre = (Genre) generoOptions.getSelectedItem();
            Language language = (Language) languageOptions.getSelectedItem();

            if (title.isEmpty() || durText.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Título y duración son obligatorios.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int duration;
            try {
                duration = Integer.parseInt(durText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "La duración debe ser un número entero.",
                        "Error de formato",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Movie movie = new Movie(title, synopsis, duration, genre, language, "");

            DataStore.getInstance().addMovie(movie);

            JOptionPane.showMessageDialog(
                    this,
                    "Película \"" + title + "\" creada correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            if (onMovieCreated != null) {
                onMovieCreated.run();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public CreateMovieForm() {
        this(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateMovieForm().setVisible(true));
    }
}
