package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
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
@DisplayName("JdbcFilmStorage")
public class JdbcFilmStorageTest {
    private final FilmStorage filmStorage;

    static Rating testRating() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setName("G");
        return rating;
    }

    static Genre testGenre() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Криминал");
        return genre;
    }

    static Film compareTestFilm() {
        LinkedHashSet<Genre> setGenres = new LinkedHashSet<>();
        setGenres.add(testGenre());
        Film film = new Film();
        film.setId(1);
        film.setDescription("Гангстеры делят наркоферму");
        film.setName("Джентельмены");
        film.setRating(testRating().getName()); // Преобразуем Rating в String
        film.setGenre(String.valueOf(setGenres));
        film.setDuration(Duration.ofMinutes(113)); // Устанавливаем Duration в минутах
        film.setReleaseDate(LocalDate.of(2019, 12, 3));
        return film;
    }

    static Film createTestFilm(Integer id) {
        LinkedHashSet<Genre> setGenres = new LinkedHashSet<>();
        setGenres.add(testGenre());
        Film film = new Film();
        if (id != 0) {
            film.setId(id);
        }
        film.setDescription("Описание к фильму");
        film.setName("Фильм новый");
        film.setRating(testRating().getName()); // Преобразуем Rating в String
        film.setGenre(String.valueOf(setGenres));
        film.setDuration(Duration.ofMinutes(113)); // Устанавливаем Duration в минутах
        film.setReleaseDate(LocalDate.of(2019, 12, 3));

        return film;
    }

    @Test
    @DisplayName("должен находиться фильм по ID")
    public void shouldReturnFilmById() {
        Film film = filmStorage.getFilmById(1); // Убираем Optional и работаем с возвращаемым объектом напрямую
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
                .isEqualTo(createTestFilm(2));
    }

    @Test
    @DisplayName("должен обновить название фильма")
    public void shouldUpdateFilm() {
        Film film = filmStorage.getFilmById(1); // Убираем Optional и работаем с возвращаемым объектом напрямую
        film.setName("Новое_Имя_Фильма");
        filmStorage.updateFilm(film); // Используем filmStorage
        Film updatedFilm = filmStorage.getFilmById(1); // Получаем обновленный фильм

        assertThat(updatedFilm.getName())
                .isEqualTo("Новое_Имя_Фильма");
    }
}
