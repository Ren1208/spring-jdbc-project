package ru.semenchenko.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.semenchenko.library.models.Book;
import ru.semenchenko.library.models.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author Artyom Semenchenko
 */

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE person_id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE NAME = ?",
                        new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO PERSON(name, year_of_birth) VALUES(?, ?)",
                person.getName(),
                person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE PERSON SET name = ?, year_of_birth = ? WHERE person_id = ?",
                updatedPerson.getName(),
                updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE person_id = ?", id);
    }

    public Optional<List<Book>> personListBooks(int personId) {
        return Optional.of(jdbcTemplate.query("SELECT * FROM BOOK WHERE person_id = ?",
                new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class)));
    }

}
