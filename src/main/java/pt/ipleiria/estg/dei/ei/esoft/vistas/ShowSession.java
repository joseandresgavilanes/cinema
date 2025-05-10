package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;

public class ShowSession extends JFrame {
    private JButton button1;
    private JButton button2;
    private JPanel SessionPanel;
    private JPanel MovieContainer;

    public static void main(String[] args) {
        new ShowSession().setVisible(true);
    }
    public ShowSession() {
        setContentPane(SessionPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        MovieContainer.setLayout(new BoxLayout(MovieContainer, BoxLayout.Y_AXIS));
        
    }
}
