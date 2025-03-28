package ru.semenchenko.library.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Artyom Semenchenko
 */

public class Book {
    private int bookId;

    @NotEmpty(message = "Название книги не может быть пустым.")
    @Size(min = 2, max = 200, message = "Название книги должно содержать от 2 до 100 символов.")
    private String name;

    @NotEmpty(message = "ФИО автора не может быть пустым.")
    @Size(min = 4, max = 200, message = "ФИО автора должно содержать от 4 до 200 символов.")
    private String author;

    @Max(value = 2024, message = "Год выпуска книги не может быть позднее 2024 года.")
    private int yearOfRelease;

    public Book() {}

    public Book(String author, int yearOfRelease, String name, int bookId) {
        this.author = author;
        this.yearOfRelease = yearOfRelease;
        this.name = name;
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}
