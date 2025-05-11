package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.util.List;

public class ShowMoviePanel extends JPanel {
    private JPanel MoviePanel;
    private JComboBox comboBox1;
    private JButton BuyButton;
    private JLabel lblTitle;
    private JLabel lblDescription;

    public void setTitulo(String titulo) {
        lblTitle.setText(titulo);
    }

    public void setDescripcion(String descripcion) {
        lblDescription.setText(descripcion);
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

    public JPanel getMoviePanel() {
        return MoviePanel;
    }
}
