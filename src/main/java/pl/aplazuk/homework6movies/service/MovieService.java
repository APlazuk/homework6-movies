package pl.aplazuk.homework6movies.service;

import org.springframework.stereotype.Service;
import pl.aplazuk.homework6movies.aspects.MovieAspect;
import pl.aplazuk.homework6movies.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private List<Movie> movies;

    public MovieService() {
        movies = new ArrayList<Movie>();
        movies.add(new Movie("The Shawshank Redemption", "1994", "Castle Rock Entertainment, Columbia Pictures"));
        movies.add(new Movie("Intouchables", "2011", "Canal+"));
        movies.add(new Movie("The Green Mile", "1999", "Frank Darabont, David Valdes"));
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @MovieAspect
    public void addMovies(Movie movie) {
        movies.add(movie);
    }
}
