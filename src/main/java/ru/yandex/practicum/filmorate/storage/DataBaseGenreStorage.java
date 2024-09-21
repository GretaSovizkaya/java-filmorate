package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Genres;
import ru.yandex.practicum.filmorate.storage.mappers.GenresMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DataBaseGenreStorage implements GenreStorage {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Collection<Genres> getGenres() {
        final String SELECT_QUERY = "SELECT GENRE_ID, NAME_GENRE FROM GENRE";
        return jdbc.query(SELECT_QUERY, new GenresMapper());
    }

    @Override
    public Optional<Genres> findById(Long id) {
        final String SELECT_QUERY = "SELECT GENRE_ID, NAME_GENRE FROM GENRE WHERE GENRE_ID = :genreId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("genreId", id);

        try {
            return Optional.ofNullable(jdbc.queryForObject(SELECT_QUERY, parameters, new GenresMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genres> findByIds(List<Long> genreIds) {
        final String SELECT_QUERY_IN = "SELECT GENRE_ID, NAME_GENRE FROM GENRE WHERE GENRE_ID IN (:genreIds)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("genreIds", genreIds);
        return jdbc.query(SELECT_QUERY_IN, parameters, new GenresMapper());
    }

    @Override
    public Genres create(Genres genres) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        final String INSERT_GENRE_QUERY = "INSERT INTO GENRE (NAME_GENRE) VALUES (:genreName)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("genreName", genres.getName());

        jdbc.update(INSERT_GENRE_QUERY, parameters, keyHolder);
        genres.setId(keyHolder.getKeyAs(Long.class));
        return genres;
    }

    @Override
    public Genres update(Genres genres) {
        final String UPDATE_GENRE_QUERY = "UPDATE GENRE SET NAME_GENRE = :genreName WHERE GENRE_ID = :genreId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("genreId", genres.getId());
        parameters.addValue("genreName", genres.getName());
        jdbc.update(UPDATE_GENRE_QUERY, parameters);

        return genres;
    }

    @Override
    public void delete(Genres genres) {
        final String DELETE_GENRE_QUERY = "DELETE FROM GENRE WHERE GENRE_ID = :genreId";
        final String DELETE_FILM_GENRES_QUERY = "DELETE FROM GENRES_FILM WHERE GENRE_ID = :genreId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("genreId", genres.getId());

        jdbc.update(DELETE_GENRE_QUERY, parameters);
        jdbc.update(DELETE_FILM_GENRES_QUERY, parameters);
    }
    @Override
    public List<Genres> getAllGenres() {
        String sql = "SELECT GENRE_ID, NAME_GENRE FROM GENRE";
        return jdbc.query(sql, new GenresMapper());
    }

    @Override
    public Genres getGenreById(int id) {
        String sql = "SELECT GENRE_ID, NAME_GENRE FROM GENRE WHERE GENRE_ID = :genreId";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("genreId", id);

        try {
            return jdbc.queryForObject(sql, parameters, new GenresMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
