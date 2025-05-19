package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Room;
import pt.ipleiria.estg.dei.ei.esoft.models.SoundSystem;

import javax.swing.*;

public class CreateRoomForm extends JFrame {
    private JPanel adminPanel;
    private JTextField nameTextField;
    private JTextField capacityTextField;
    private JCheckBox accessibilityCheckBox;
    private JComboBox<SoundSystem> comboBoxSoundsSystem;
    private JButton cancelButton;
    private JButton saveButton;

    private final Runnable onRoomCreated;

    public CreateRoomForm(Runnable onRoomCreated) {
        this.onRoomCreated = onRoomCreated;

        setTitle("Create Room");
        setContentPane(adminPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        for (SoundSystem soundSystem : SoundSystem.values()) {
            comboBoxSoundsSystem.addItem(soundSystem);
        }

        cancelButton.addActionListener(e -> dispose());

        saveButton.addActionListener(e -> {
            String name = nameTextField.getText().trim();
            String capacityStr = capacityTextField.getText().trim();
            boolean accessible = accessibilityCheckBox.isSelected();
            SoundSystem soundSystem = (SoundSystem) comboBoxSoundsSystem.getSelectedItem();

            if (name.isEmpty() || capacityStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and capacity are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int capacity;
            try {
                capacity = Integer.parseInt(capacityStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Capacity must be a number.", "Format Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear Room
            Room room = new Room(name, capacity, accessible, soundSystem);
            DataStore.getInstance().addRoom(room);

            JOptionPane.showMessageDialog(this, "Room created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();

            if (onRoomCreated != null) {
                onRoomCreated.run();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateRoomForm(null).setVisible(true));
    }
}
