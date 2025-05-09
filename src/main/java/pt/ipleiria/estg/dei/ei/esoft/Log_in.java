package pt.ipleiria.estg.dei.ei.esoft;

import javax.swing.*;

public class Log_in extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JTextField passwordTextField;
    private JTextField userTextField;
    private JLabel imgUser;
    private JLabel imgPassword;
    private JButton loginButton;

    public Log_in() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

    }

    public static void main(String[] args) {
        new Log_in().setVisible(true);
   }






}
