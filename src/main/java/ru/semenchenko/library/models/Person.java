package ru.semenchenko.library.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Artyom Semenchenko
 */

public class Person {
    private int personId;

    @NotEmpty(message = "ФИО не может быть пустым.")
    @Size(min = 4, max = 200, message = "ФИО должно содержать от 4 до 200 символов.")
    private String name;

    @Min(value = 1900, message = "Год рождения должнен быть больше 1900.")
    @Max(value = 2024, message = "Год рождения должен быть меньше 2025.")
    private int yearOfBirth;

    public Person() {}

    public Person(int personId, String name, int yearOfBirth) {
        this.personId = personId;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
