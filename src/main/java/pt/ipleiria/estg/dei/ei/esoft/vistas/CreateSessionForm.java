package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;
import pt.ipleiria.estg.dei.ei.esoft.models.Room;
import pt.ipleiria.estg.dei.ei.esoft.models.Session;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateSessionForm extends JFrame {
    private JPanel panel;
    private JComboBox<Movie> movieOptions;
    private JComboBox<Room> roomOptions;
    private JTextField scheduleField;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel buttons;

    private final Runnable onSessionSaved;
    private final Session editingSession;

    /**
     * @param sessionToEdit   si es null → crea nueva; si no null → edita esta sesión
     * @param onSessionSaved  callback para recargar tabla tras guardar
     */
    public CreateSessionForm(Session sessionToEdit, Runnable onSessionSaved) {
        this.editingSession = sessionToEdit;
        this.onSessionSaved = onSessionSaved;
        panel.setBackground(Color.DARK_GRAY);
        buttons.setBackground(Color.DARK_GRAY);

        setTitle(editingSession != null ? "Edit Session" : "Create Session");
        setContentPane(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComboBoxes();
        if (editingSession != null) {
            populateFieldsFrom(editingSession);
        }

        cancelButton.addActionListener(e -> dispose());

        saveButton.addActionListener(e -> {
            Movie movie = (Movie) movieOptions.getSelectedItem();
            Room room   = (Room) roomOptions.getSelectedItem();
            String schedule = scheduleField.getText().trim();

            if (movie == null || room == null || schedule.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "All fields are required.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            DataStore ds = DataStore.getInstance();
            if (editingSession != null) {
                // — Modo EDICIÓN —
                editingSession.setMovie(movie);
                editingSession.setRoom(room);
                editingSession.setSchedule(schedule);
                ds.updateSession(editingSession);

                JOptionPane.showMessageDialog(this,
                        "Session updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // — Modo CREACIÓN —
                String name = movie.getTitle() + " - " + room.getName() + " (" + schedule + ")";
                Session s = new Session(name, movie, room, schedule);
                ds.addSession(s);

                JOptionPane.showMessageDialog(this,
                        "Session created successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            if (onSessionSaved != null) {
                onSessionSaved.run();
            }
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void initComboBoxes() {
        DataStore ds = DataStore.getInstance();
        List<Movie> movies = ds.getMovies();
        List<Room> rooms   = ds.getRooms();

        DefaultComboBoxModel<Movie> movieModel = new DefaultComboBoxModel<>();
        for (Movie m : movies) movieModel.addElement(m);
        movieOptions.setModel(movieModel);

        DefaultComboBoxModel<Room> roomModel = new DefaultComboBoxModel<>();
        for (Room r : rooms) roomModel.addElement(r);
        roomOptions.setModel(roomModel);
    }

    private void populateFieldsFrom(Session s) {
        movieOptions.setSelectedItem(s.getMovie());
        roomOptions.setSelectedItem(s.getRoom());
        scheduleField.setText(s.getSchedule());
    }

    // Ejemplo de main para pruebas
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Modo creación:
            new CreateSessionForm(null, () -> {}).setVisible(true);

            // Modo edición (suponiendo que existe al menos una):
            // Session first = DataStore.getInstance().getSessions().get(0);
            // new CreateSessionForm(first, () -> {}).setVisible(true);
        });
    }
}
