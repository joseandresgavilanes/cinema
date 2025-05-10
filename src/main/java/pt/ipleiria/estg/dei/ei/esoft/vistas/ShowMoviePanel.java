package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.util.List;

public class ShowMoviePanel extends JPanel {
    private JPanel MoviePanel;
    private JComboBox comboBox1;
    private JButton BuyButton;
    private JLabel MovieTitle;
    private JLabel Description;

    public void setTitulo(String titulo) {
        MovieTitle.setText(titulo);
    }

    public void setDescripcion(String descripcion) {
        Description.setText(descripcion);
    }

    public void setHorarios(List<String> horarios) {
        comboBox1.removeAllItems();
        for (String h : horarios) {
            comboBox1.addItem(h);
        }
    }
    public ShowMoviePanel() {
        add(MoviePanel);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
