package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomsAdminPanel extends JFrame {
    private JTable roomsTable;
    private JButton addRoomButton;
    private JPanel mainPanel;

    public RoomsAdminPanel() {
        setTitle("Rooms Administration");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"Name", "Capacity", "Accessible", "Sound System"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        roomsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(roomsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        loadRooms(tableModel);

        addRoomButton = new JButton("Add New Room");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addRoomButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        addRoomButton.addActionListener(e -> {
            CreateRoomForm createForm = new CreateRoomForm(() -> {
                loadRooms((DefaultTableModel) roomsTable.getModel());
            });
            createForm.setVisible(true);
        });

        add(mainPanel);
    }

    private void loadRooms(DefaultTableModel model) {
        model.setRowCount(0);
        List<Room> rooms = DataStore.getInstance().getRooms();

        for (Room room : rooms) {
            model.addRow(new Object[]{
                    room.getName(),
                    room.getCapacity(),
                    room.getAccessibility() ? "Yes" : "No",
                    room.getSoundSystem().getDisplayName()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomsAdminPanel().setVisible(true));
    }
}
