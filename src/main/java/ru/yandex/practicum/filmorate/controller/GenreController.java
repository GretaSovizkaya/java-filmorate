package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genres;
import ru.yandex.practicum.filmorate.service.GenreService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public Collection<Genres> getGenreList() {
        return genreService.getGenreList();
    }

    @GetMapping("{id}")
    public Genres findGenre(@PathVariable long id) {
        return genreService.findById(id);
    }

    @GetMapping("/genres")
    public List<Genres> getAllGenres() {
        log.info("Запрос на получение всех жанров");
        return genreService.getAllGenres();
    }

    @GetMapping("/genres/{id}")
    public Genres getGenreById(@PathVariable int id) {
        log.info("Запрос на получение жанра по id: {}", id);
        return genreService.getGenreById(id);
    }
}