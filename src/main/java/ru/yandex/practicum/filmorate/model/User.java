package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;


import java.time.Instant;
import java.time.LocalDate;

@Data
public class User {
    private int id;

    public User() {

    }

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Login cannot be empty")
    private String login;

    private String name;

    @NonNull
    @PastOrPresent(message = "Birthday cannot be in the future")
    private LocalDate birthday;

}
