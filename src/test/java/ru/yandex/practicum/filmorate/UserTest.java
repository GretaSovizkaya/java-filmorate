package ru.yandex.practicum.filmorate;
import ru.yandex.practicum.filmorate.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.validation.*;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = FilmorateApplication.class)
public class UserTest {


    private final Validator validator;

    public UserTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUserEmailNotBlank() {
        User user = new User();  // Use the no-arg constructor
        user.setBirthday(LocalDate.of(2000, 1, 1));  // Set the required fields
        user.setEmail("");  // Empty email
        user.setLogin("login");
        user.setName("name");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Email cannot be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void testUserEmailValid() {
        User user = new User();  // Use the no-arg constructor
        user.setBirthday(LocalDate.of(2000, 1, 1));  // Set the required fields
        user.setEmail("invalid-email");  // Invalid email
        user.setLogin("login");
        user.setName("name");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Email should be valid", violations.iterator().next().getMessage());
    }

    @Test
    public void testUserLoginNotBlank() {
        User user = new User();  // Use the no-arg constructor
        user.setBirthday(LocalDate.of(2000, 1, 1));  // Set the required fields
        user.setEmail("user@example.com");
        user.setLogin("");  // Empty login
        user.setName("name");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Login cannot be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void testUserBirthdayNotInFuture() {
        User user = new User();  // Use the no-arg constructor
        user.setBirthday(LocalDate.of(3000, 1, 1));  // Birthday in the future
        user.setEmail("user@example.com");
        user.setLogin("login");
        user.setName("name");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Birthday cannot be in the future", violations.iterator().next().getMessage());
    }
}
