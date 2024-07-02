package pl.aplazuk.homework6movies.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.aplazuk.homework6movies.model.Movie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Movie movie, String addresses, String subject, String recipientName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject(subject);
        message.setRecipients(MimeMessage.RecipientType.TO, addresses);

        String htmlTemplate = readHtmlTemplate(movie);
        String htmlContent = htmlTemplate.replace("${name}", recipientName);
        htmlContent = htmlContent.replace("${title}", movie.getTitle());
        htmlContent = htmlContent.replace("${year}", movie.getYear());
        htmlContent = htmlContent.replace("${producer}", movie.getProducer());

        message.setContent(htmlContent, "text/html;charset=utf-8");
        mailSender.send(message);
    }

    private String readHtmlTemplate(Movie movie) {
        try {
            return readTemplateHtmlFile("src/main/resources/templates/email.template.html");
        } catch (IOException e) {
            return String.format("Your movie hase been added to your favorite list." +
                    " Title: %1s, Year: %2s, Producer: %3s", movie.getTitle(), movie.getYear(), movie.getProducer());
        }
    }

    private String readTemplateHtmlFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}
