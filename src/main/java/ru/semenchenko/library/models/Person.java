package ru.semenchenko.library.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Artyom Semenchenko
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int personId;

    @NotEmpty(message = "ФИО не может быть пустым.")
    @Size(min = 4, max = 100, message = "ФИО должно содержать от 4 до 100 символов.")
    private String name;

    @Min(value = 1900, message = "Год рождения должнен быть больше 1900.")
    @Max(value = 2024, message = "Год рождения должен быть меньше 2025.")
    private int yearOfBirth;
}
