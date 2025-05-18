package pt.ipleiria.estg.dei.ei.esoft;

import java.util.List;

public class Movie {
    private String titulo;
    private String descripcion;
    private List<String> horarios;

    public Movie(String titulo, String descripcion, List<String> horarios) {
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
