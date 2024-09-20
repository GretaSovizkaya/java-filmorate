package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Rating;
import ru.yandex.practicum.filmorate.storage.mappers.RatingMapper;

import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DataBaseRatingStorage implements RatingStorage {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Optional<Rating> findById(Integer id) {
        final String SELECT_QUERY = "SELECT FILM_ID, RATING_NAME FROM RATING_MPA WHERE FILM_ID = :filmId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("filmId", id);

        try {
            return Optional.ofNullable(jdbc.queryForObject(SELECT_QUERY, parameters, new RatingMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Rating> getRatingList() {
        final String SELECT_QUERY = "SELECT FILM_ID, RATING_NAME FROM RATING_MPA";
        return jdbc.query(SELECT_QUERY, new RatingMapper());
    }

    @Override
    public Rating create(Rating rating) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        final String INSERT_RATING_QUERY = "INSERT INTO RATING_MPA (RATING_NAME) VALUES (:ratingName)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ratingName", rating.getName());

        jdbc.update(INSERT_RATING_QUERY, parameters, keyHolder);
        rating.setId(keyHolder.getKeyAs(Integer.class));
        return rating;
    }

    @Override
    public Rating update(Rating rating) {
        final String UPDATE_RATING_QUERY = "UPDATE RATING_MPA SET RATING_NAME = :ratingName WHERE FILM_ID = :filmId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("filmId", rating.getId());
        parameters.addValue("ratingName", rating.getName());

        jdbc.update(UPDATE_RATING_QUERY, parameters);
        return rating;
    }

    @Override
    public void delete(Rating rating) {
        final String DELETE_RATING_QUERY = "DELETE FROM RATING_MPA WHERE FILM_ID = :filmId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("filmId", rating.getId());

        jdbc.update(DELETE_RATING_QUERY, parameters);
    }
    @Override
    public Rating getRatingById(int id) {
        String sql = "SELECT FILM_ID, RATING_NAME FROM RATING_MPA WHERE FILM_ID = :filmId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("filmId", id);

        try {
            return jdbc.queryForObject(sql, parameters, new RatingMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // or handle it according to your application's logic
        }
    }

}
