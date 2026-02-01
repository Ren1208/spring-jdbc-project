package ru.semenchenko.library.models;

import jakarta.validation.constraints.Max;
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
public class Book {
    private int bookId;

    @NotEmpty(message = "Название книги не может быть пустым.")
    @Size(min = 2, max = 100, message = "Название книги должно содержать от 2 до 100 символов.")
    private String name;

    @NotEmpty(message = "Имя автора не может быть пустым.")
    @Size(min = 4, max = 100, message = "Имя автора должно содержать от 4 до 100 символов.")
    private String author;

    @Max(value = 2026, message = "Год выпуска книги не может быть позднее 2026 года.")
    private int yearOfRelease;
}
