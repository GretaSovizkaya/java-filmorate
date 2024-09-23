package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Rating;
import ru.yandex.practicum.filmorate.storage.DataBaseRatingStorage;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class RatingDbStorageTest {
    private final DataBaseRatingStorage ratingDbStorage;

    @Test
    public void getRatingById() {
        Rating rating = ratingDbStorage.getRatingById(1);

        assertThat(rating).hasFieldOrPropertyWithValue("id",1);

    }

}