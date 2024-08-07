package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Film {

    private int id;

    public Film() {

    }

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @Size(max=200, message = "Description is too long!")
    private String description;

    @NonNull
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    private Duration duration;

}
