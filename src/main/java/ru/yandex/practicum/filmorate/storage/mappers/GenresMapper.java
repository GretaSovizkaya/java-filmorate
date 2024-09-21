package ru.yandex.practicum.filmorate.storage.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Genres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenresMapper implements RowMapper<Genres> {
    @Override
    public Genres mapRow(ResultSet rs, int rowNum) throws SQLException {
        Genres genres = new Genres();
        genres.setId((long) rs.getInt("id"));
        genres.setName(rs.getString("name_genre"));
        return genres;
    }
}
