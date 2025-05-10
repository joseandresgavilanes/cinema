package pt.ipleiria.estg.dei.ei.esoft;

import java.util.List;

public class Pelicula {
    private String titulo;
    private String descripcion;
    private List<String> horarios;

    public Pelicula(String titulo, String descripcion, List<String> horarios) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.horarios = horarios;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getHorarios() {
        return horarios;
    }
}
