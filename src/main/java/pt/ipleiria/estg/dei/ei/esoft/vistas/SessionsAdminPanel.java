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
    private JPanel mainPanel;

    public SessionsAdminPanel() {
        setTitle("Sessions Administration");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"Title", "Room", "Date & Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        sessionsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(sessionsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        loadSessions(tableModel);

        addSessionButton = new JButton("Add New Session");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addSessionButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        addSessionButton.addActionListener(e -> {
            CreateSessionForm createForm = new CreateSessionForm(() -> {
                loadSessions((DefaultTableModel) sessionsTable.getModel());
            });
            createForm.setVisible(true);
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
