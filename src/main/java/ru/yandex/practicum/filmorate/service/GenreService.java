package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.GenreStorage;
import ru.yandex.practicum.filmorate.storage.GenreStorage;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreStorage genreRepository;

    public Collection<Genre> getGenreList() {
        return genreRepository.getGenres();
    }

    public Genre findById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Жанр не найден"));
    }
}