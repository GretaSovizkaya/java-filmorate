package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Rating;

import java.util.List;

public interface FilmStorage {
    Film addFilm(Film film);
    Film updateFilm(Film film);
    void deleteFilm(int id);
    List<Film> getAllFilms();
    Film getFilmById(int id);
    void addLike(int filmId, int userId);
    void removeLike(int filmId, int userId);

}
