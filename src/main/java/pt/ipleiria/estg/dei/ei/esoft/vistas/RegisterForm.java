package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;

public class RegisterForm extends javax.swing.JFrame {
    private JButton createButton;
    private JPanel createUserPanel;


    public RegisterForm() {
        setContentPane(createUserPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

    }

    public static void main(String[] args) {
        new RegisterForm().setVisible(true);
    }



}
