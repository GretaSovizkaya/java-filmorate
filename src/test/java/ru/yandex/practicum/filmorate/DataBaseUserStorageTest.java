package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.DataBaseUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@AutoConfigureTestDatabase
@Import(DataBaseUserStorage.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DisplayName("DataBaseUserStorage")
class DataBaseUserStorageTest {
    private final UserStorage userStorage;
    public static final Integer FindUser_Id = 1;

    static User compareTestUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setEmail("test@mail.com");
        user.setLogin("UserName");
        user.setName("UserName");
        user.setBirthday(LocalDate.of(1990, 10, 20));
        return user;
    }

    static User createTestUser() {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setLogin("UserName");
        user.setName("UserName");
        user.setBirthday(LocalDate.of(1990, 10, 20));
        return user;
    }

    @Test
    @DisplayName("должен находиться пользователь по ID")
    public void shouldReturnUserById() {

        Optional<User> userOptional = Optional.ofNullable(userStorage.getUserById(FindUser_Id));

        assertThat(userOptional)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(compareTestUser(FindUser_Id));
    }

    @Test
    @DisplayName("должен быть получен списко всех пользователей и размер списка должен соответсвовать кол-ву Users в BD")
    public void shouldDeleteUser() {
        List<User> listUsers = userStorage.getAllUsers().stream().toList();
        assertThat(listUsers.size())
                .isEqualTo(2);
    }
}