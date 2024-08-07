package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.exception.ValidateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ru.yandex.practicum.filmorate.exception.ValidateException;
@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final List<Film> films = new ArrayList<>();
    @PostMapping
    public Film addNewFilms(@Valid @RequestBody Film film){
        validateFilm(film);
        films.add(film);
        log.info("Фильм добавлен {}", film);
        return film;
    }
    @PutMapping
    public Film update(@Valid @RequestBody Film film){
        validateFilm(film);
        log.info("Фильм обновлен: {}", film);
        return film;
    }
    @GetMapping
    public List<Film> getAllFilms(){
        return films;
    }
    public void validateFilm(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidateException("Release date cannot be before 28 December 1895");
        }
    }
}
