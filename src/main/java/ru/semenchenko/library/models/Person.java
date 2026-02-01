package ru.semenchenko.library.models;

import jakarta.validation.constraints.*;
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

    @Pattern(
            regexp = "^(?=.{4,50}$)(?!\\s*$)[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+(?:\\s[А-ЯЁ][а-яё]+)?$",
            message = "Неверный формат ФИО. Требования: 1) Непустое поле; 2) От 4 до 50 символов; 3) Формат 'Фамилия Имя (Отчество)'; 4) Только кириллица, каждое слово с заглавной буквы"
    )
    private String name;

    @Min(value = 1900, message = "Год рождения должнен быть больше 1900.")
    @Max(value = 2024, message = "Год рождения должен быть меньше 2025.")
    private int yearOfBirth;
}
