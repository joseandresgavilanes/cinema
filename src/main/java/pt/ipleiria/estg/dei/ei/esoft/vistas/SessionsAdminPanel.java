package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SessionsAdminPanel extends JFrame {
    private JTable sessionsTable;
    private JButton addSessionButton;
    private JButton editSessionButton;
    private JButton deleteSessionButton;
    private JPanel mainPanel;

    public SessionsAdminPanel() {
        setTitle("Sessions Administration");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        // — tabla —
        String[] columnNames = {"Title", "Room", "Date & Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        sessionsTable = new JTable(tableModel);
        mainPanel.add(new JScrollPane(sessionsTable), BorderLayout.CENTER);
        loadSessions(tableModel);

        // — botones abajo —
        addSessionButton    = new JButton("Add New Session");
        editSessionButton   = new JButton("Edit Selected");
        deleteSessionButton = new JButton("Delete Selected");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addSessionButton);
        bottomPanel.add(editSessionButton);
        bottomPanel.add(deleteSessionButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // — listeners —
        addSessionButton.addActionListener(e -> {
            new CreateSessionForm(null, () -> loadSessions(tableModel))
                    .setVisible(true);
        });

        editSessionButton.addActionListener(e -> {
            int row = sessionsTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this,
                        "Please select a session to edit.",
                        "No selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            Session s = DataStore.getInstance().getSessions().get(row);
            new CreateSessionForm(s, () -> loadSessions(tableModel))
                    .setVisible(true);
        });

        deleteSessionButton.addActionListener(e -> {
            int row = sessionsTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this,
                        "Please select a session to delete.",
                        "No selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int ok = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this session?",
                    "Confirm delete",
                    JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                Session s = DataStore.getInstance().getSessions().get(row);
                DataStore.getInstance().removeSession(s);
                loadSessions(tableModel);
            }
        });

        add(mainPanel);
    }

    private void loadSessions(DefaultTableModel model) {
        model.setRowCount(0);
        List<Session> sessions = DataStore.getInstance().getSessions();
        for (Session session : sessions) {
            model.addRow(new Object[]{
                    session.getMovie().getTitle(),
                    session.getRoom().getName(),
                    session.getSchedule()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SessionsAdminPanel().setVisible(true));
    }
}
