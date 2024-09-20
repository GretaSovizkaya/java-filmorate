package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenreStorage {
    Optional<Genre> findById(Long id);

    Collection<Genre> findByIds(List<Long> genreIds);

    Collection<Genre> getGenres();

    Genre create(Genre genre);

    void delete(Genre genre);

    Genre update(Genre genre);
    List<Genre> getAllGenres();
    Genre getGenreById(int id);
}