package ru.yandex.practicum.filmorate;

import ru.yandex.practicum.filmorate.model.Film;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.validation.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FilmTest {

    private final Validator validator;

    public FilmTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testFilmNameNotBlank() {
        Film film = new Film();  // указываем обязательное поле releaseDate
        film.setName("");  // пустое название
        film.setDescription("Description");
        film.setDuration(Duration.ofMinutes(120));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(1, violations.size());
        assertEquals("Name cannot be empty!", violations.iterator().next().getMessage());
    }

    @Test
    public void testFilmDescriptionTooLong() {
        Film film = new Film();  // указываем обязательное поле releaseDate
        film.setName("Film Name");
        film.setDescription("A".repeat(201));  // слишком длинное описание
        film.setDuration(Duration.ofMinutes(120));

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(1, violations.size());
        assertEquals("Description is too long!", violations.iterator().next().getMessage());
    }
}