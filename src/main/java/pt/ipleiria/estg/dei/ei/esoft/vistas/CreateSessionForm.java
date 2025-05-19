package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;
import pt.ipleiria.estg.dei.ei.esoft.models.Room;
import pt.ipleiria.estg.dei.ei.esoft.models.Session;

import javax.swing.*;
import java.util.List;

public class CreateSessionForm extends JFrame {
    private JPanel panel;
    private JComboBox<Movie> movieOptions;
    private JComboBox<Room> roomOptions;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField scheduleField;

    private final Runnable onSessionCreated;

    public CreateSessionForm(Runnable onSessionCreated) {
        this.onSessionCreated = onSessionCreated;

        setTitle("Create Session");
        setContentPane(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DataStore ds = DataStore.getInstance();

        for (Movie movie : ds.getMovies()) {
            movieOptions.addItem(movie);
        }
        for (Room room : ds.getRooms()) {
            roomOptions.addItem(room);
        }

        cancelButton.addActionListener(e -> dispose());

        saveButton.addActionListener(e -> {
            Movie movie = (Movie) movieOptions.getSelectedItem();
            Room room = (Room) roomOptions.getSelectedItem();
            String schedule = scheduleField.getText().trim();

            if (movie == null || room == null || schedule.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sessionName = movie.getTitle() + " - " + room.getName() + " (" + schedule + ")";
            Session session = new Session(sessionName, movie, room, schedule);
            ds.addSession(session);

            JOptionPane.showMessageDialog(this, "Session created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();

            if (onSessionCreated != null) {
                onSessionCreated.run();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateSessionForm(null).setVisible(true));
    }
}
