package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genres;
import ru.yandex.practicum.filmorate.storage.GenreStorage;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreStorage genreStorage;

    public Collection<Genres> getGenreList() {
        return genreStorage.getGenres();
    }

    public Genres findById(long id) {
        return genreStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Жанр не найден"));
    }

    public List<Genres> getAllGenres() {
        return genreStorage.getAllGenres();
    }

    public Genres getGenreById(int id) {
        return genreStorage.getGenreById(id);
    }
}