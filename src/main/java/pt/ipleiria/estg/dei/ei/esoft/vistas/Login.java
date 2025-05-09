package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;

public class Login extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JTextField passwordTextField;
    private JTextField userTextField;
    private JLabel imgUser;
    private JLabel imgPassword;
    private JButton loginButton;

    public Login() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

    }

    public static void main(String[] args) {
        new Login().setVisible(true);
   }






}
