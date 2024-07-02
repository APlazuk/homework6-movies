package pl.aplazuk.homework6movies.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pl.aplazuk.homework6movies.model.Movie;
import pl.aplazuk.homework6movies.service.EmailService;

import java.util.Arrays;

@Aspect
@Component
public class MovieAspects {

    private final EmailService emailService;
    private static final String emailSubject = "Your new favorite movie";

    public MovieAspects(EmailService emailService) {
        this.emailService = emailService;
    }

    @After("@annotation(MovieAspect)")
    public void afterAddedMovie(JoinPoint joinPoint) throws Throwable {
        Movie movie = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof Movie)
                .map(arg -> (Movie) arg)
                .findFirst()
                .orElse(null);

        if (movie != null) {
            emailService.sendEmail(movie, "example@example.com", emailSubject, "Ola");
        }
    }
}
