package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genres;
import ru.yandex.practicum.filmorate.model.Rating;
import ru.yandex.practicum.filmorate.storage.DataBaseFilmStorage;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@AutoConfigureTestDatabase
@Import(DataBaseFilmStorage.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DisplayName("DataBaseFilmStorage")
public class DataBaseFilmStorageTest {
    private final FilmStorage filmStorage;

    static Rating testRating() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setName("G");
        return rating;
    }

    static Genres testGenre() {
        Genres genres = new Genres();
        genres.setId(1L);
        genres.setName("Криминал");
        return genres;
    }

    static Film compareTestFilm() {
        LinkedHashSet<Genres> setGenres = new LinkedHashSet<>();
        setGenres.add(testGenre());
        Film film = new Film();
        film.setId(1);
        film.setDescription("Гангстеры делят наркоферму");
        film.setName("Джентельмены");
        film.setRating(testRating().getName());
        film.setGenre(String.valueOf(setGenres));
        film.setDuration(Duration.ofMinutes(113));
        film.setReleaseDate(LocalDate.of(2019, 12, 3));
        return film;
    }

    static Film createTestFilm(Integer id) {
        LinkedHashSet<Genres> setGenres = new LinkedHashSet<>();
        setGenres.add(testGenre());
        Film film = new Film();
        if (id != 0) {
            film.setId(id);
        }
        film.setDescription("Описание к фильму");
        film.setName("Фильм новый");
        film.setRating(testRating().getName());
        film.setGenre(String.valueOf(setGenres));
        film.setDuration(Duration.ofMinutes(113));
        film.setReleaseDate(LocalDate.of(2019, 12, 3));
        return film;
    }

    @BeforeEach
    public void setUp() {
        // Resetting database state for each test
        filmStorage.deleteFilm(1); // Ensure you have this method implemented in your FilmStorage
        filmStorage.addFilm(compareTestFilm()); // Prepopulate with a known film for tests
    }

    @Test
    @DisplayName("должен находиться фильм по ID")
    public void shouldReturnFilmById() {
        Film film = filmStorage.getFilmById(1);
        assertThat(film)
                .usingRecursiveComparison()
                .isEqualTo(compareTestFilm());
    }

    @Test
    @DisplayName("должен быть создан Film")
    public void shouldCreateFilm() {
        Film filmNew = filmStorage.addFilm(createTestFilm(0));

        assertThat(filmNew)
                .usingRecursiveComparison()
                .isEqualTo(createTestFilm(filmNew.getId())); // Use the actual ID of the created film
    }

    @Test
    @DisplayName("должен обновить название фильма")
    public void shouldUpdateFilm() {
        Film film = filmStorage.getFilmById(1);
        film.setName("Новое_Имя_Фильма");
        filmStorage.updateFilm(film);
        Film updatedFilm = filmStorage.getFilmById(1);

        assertThat(updatedFilm.getName())
                .isEqualTo("Новое_Имя_Фильма");
    }
}
