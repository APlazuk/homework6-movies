package pl.aplazuk.homework6movies.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Movie {

    @NotNull
    @NotBlank(message = "Please provide a movie title")
    private String title;

    @NotNull
    @NotBlank(message = "Please provide year of movie production")
    private String year;

    @NotNull
    @NotBlank(message = "Please provide movie producer")
    private String producer;

    public Movie(String title, String year, String producer) {
        this.title = title;
        this.year = year;
        this.producer = producer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
