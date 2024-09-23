package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Rating;

import java.util.Collection;
import java.util.Optional;

public interface RatingStorage {
    public Optional<Rating> findById(Integer id);

    Collection<Rating> getRatingList();

    Rating create(Rating rating);

    void delete(Rating rating);

    Rating update(Rating rating);

    Rating getRatingById(int id);
}