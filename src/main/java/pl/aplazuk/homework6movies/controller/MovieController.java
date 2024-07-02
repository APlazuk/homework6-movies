package pl.aplazuk.homework6movies.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.aplazuk.homework6movies.model.Movie;
import pl.aplazuk.homework6movies.service.MovieService;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = movieService.getMovies();
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody @Valid Movie movie, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            movieService.addMovies(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body("Movie has been added successfully, you will receive an e-mail notification");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage());
    }
}
