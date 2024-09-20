package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.validation.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = FilmorateApplication.class)
public class FilmTest {

    private final Validator validator;

    public FilmTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testFilmNameNotBlank() {
        Film film = new Film();  // Use the no-arg constructor
        film.setReleaseDate(LocalDate.of(2020, 1, 1));  // Set required fields
        film.setName("");  // Empty name
        film.setDescription("Description");
        film.setDuration(Duration.ofMinutes(120));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(1, violations.size());
        assertEquals("Name cannot be empty!", violations.iterator().next().getMessage());
    }

    @Test
    public void testFilmDescriptionTooLong() {
        Film film = new Film();  // Use the no-arg constructor
        film.setReleaseDate(LocalDate.of(2020, 1, 1));  // Set required fields
        film.setName("Film Name");
        film.setDescription("A".repeat(201));  // Description too long
        film.setDuration(Duration.ofMinutes(120));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(1, violations.size());
        assertEquals("Description is too long!", violations.iterator().next().getMessage());
    }
}