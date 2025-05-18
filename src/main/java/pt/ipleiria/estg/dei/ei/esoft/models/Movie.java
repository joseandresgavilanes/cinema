package pt.ipleiria.estg.dei.ei.esoft.models;

import java.util.List;

public class Movie {
    private int movieId;
    private String title;
    private String description;
    private Integer duration;
    private Genre genre;
    private Language language;
    private String imagePath;

    public Movie(String title, String description, Integer duration, Genre genre, Language language, String imagePath) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.genre = genre;
        this.language = language;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
