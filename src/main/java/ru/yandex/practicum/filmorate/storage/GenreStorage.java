package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Genres;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenreStorage {
    Optional<Genres> findById(Long id);

    Collection<Genres> findByIds(List<Long> genreIds);

    Collection<Genres> getGenres();

    Genres create(Genres genres);

    void delete(Genres genres);
    Genres update(Genres genres);
    List<Genres> getAllGenres();
    Genres getGenreById(int id);
}