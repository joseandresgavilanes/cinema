package pt.ipleiria.estg.dei.ei.esoft;

import javax.swing.*;

public class Admin extends JFrame {
    private JPanel adminPanel;
    private JTextField tituloTextField;
    private JTextField duracionTextField;
    private JTextField generoTextField;
    private JTextField idiomaTextField;
    private JTextField sinopsisTextField;
    private JButton cancelButton;
    private JButton saveButton;

    public Admin() {
        setContentPane(adminPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

    }

    public static void main(String[] args) {
        new Admin().setVisible(true);

    }



}
