package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.DataBaseUserStorage;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserDbStorageTest {
    private final DataBaseUserStorage dataBaseUserStorage;

    @Test
    public void checkCreateNewUserAndGetById () {

        User user = new User();

        dataBaseUserStorage.addUser(user);

        User user1 = dataBaseUserStorage.getUserById(user.getId());

        assertThat(user1).hasFieldOrPropertyWithValue("id",1L);

        dataBaseUserStorage.deleteUser(user.getId());
    }

    @Test
    public void checkGetAllUsers () {
        User user = new User();

        dataBaseUserStorage.addUser(user);

        List<User> users = dataBaseUserStorage.getAllUsers();

        Assertions.assertEquals(users.size(),1);

        dataBaseUserStorage.deleteUser(user.getId());
    }

    @Test
    public void updateUserAndGetById() {
        User user = new User();
        dataBaseUserStorage.addUser(user);

        user.setName("Rita");
        dataBaseUserStorage.updateUser(user);

        User user1 = dataBaseUserStorage.getUserById(user.getId());

        assertThat(user1).hasFieldOrPropertyWithValue("name","Rita");


        dataBaseUserStorage.deleteUser(user.getId());
    }

    @Test
    public void compareUsers() {

        User user = new User();
        User user1 = new User();
        Assertions.assertEquals(user1,user);

    }

}