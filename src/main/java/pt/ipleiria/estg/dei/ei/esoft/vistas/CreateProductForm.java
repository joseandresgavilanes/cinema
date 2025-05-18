package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;

public class CreateProductForm extends JFrame {
    private JPanel adminPanel;
    private JTextField tituloTextField;
    private JTextField duracionTextField;
    private JTextField generoTextField;
    private JTextField idiomaTextField;
    private JTextField sinopsisTextField;
    private JButton cancelButton;
    private JButton saveButton;

    public CreateProductForm() {
        setContentPane(adminPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

    }

    public static void main(String[] args) {
        new CreateMovieForm().setVisible(true);

    }

}
