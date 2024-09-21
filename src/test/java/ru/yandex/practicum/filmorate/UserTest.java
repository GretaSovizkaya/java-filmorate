package ru.yandex.practicum.filmorate;

import ru.yandex.practicum.filmorate.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.validation.*;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {

    private final Validator validator;

    public UserTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUserEmailNotBlank() {
        User user = new User();  // указываем обязательное поле birthday
        user.setEmail("");  // пустой email
        user.setLogin("login");
        user.setName("name");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Email cannot be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void testUserEmailValid() {
        User user = new User();  // указываем обязательное поле birthday
        user.setEmail("invalid-email");  // некорректный email
        user.setLogin("login");
        user.setName("name");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Email should be valid", violations.iterator().next().getMessage());
    }

    @Test
    public void testUserLoginNotBlank() {
        User user = new User();  // указываем обязательное поле birthday
        user.setEmail("user@example.com");
        user.setLogin("");  // пустой логин
        user.setName("name");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Login cannot be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void testUserBirthdayNotInFuture() {
        User user = new User();  // указываем обязательное поле birthday
        user.setEmail("user@example.com");
        user.setLogin("login");
        user.setName("name");
        user.setBirthday(LocalDate.of(3000, 1, 1));  // дата рождения в будущем

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Birthday cannot be in the future", violations.iterator().next().getMessage());
    }
}