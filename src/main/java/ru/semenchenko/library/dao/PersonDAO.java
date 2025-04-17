package ru.semenchenko.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
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

    private final JdbcClient jdbcClient;

    @Autowired
    public PersonDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Person> index() {
        return jdbcClient.sql("SELECT * FROM PERSON")
                .query(Person.class)
                .list();
    }

    public Person show(int id) {
        return jdbcClient.sql("SELECT * FROM PERSON WHERE person_id = ?")
                .param(id)
                .query(Person.class)
                .single();
    }

    public void save(Person person) {
        jdbcClient.sql("INSERT INTO PERSON(name, year_of_birth) VALUES(?, ?)")
                .param(person.getName())
                .param(person.getYearOfBirth())
                .update();

    }

    public void update(int id, Person updatedPerson) {
        jdbcClient.sql("UPDATE PERSON SET name = ?, year_of_birth = ? WHERE person_id = ?")
                .param(updatedPerson.getName())
                .param(updatedPerson.getYearOfBirth())
                .param(id)
                .update();
    }

    public void delete(int id) {
        jdbcClient.sql("DELETE FROM PERSON WHERE person_id = ?")
                .param(id)
                .update();
    }

    public Optional<Person> getPersonByFullName(String name) {
        return jdbcClient.sql("SELECT * FROM Person WHERE name = ?")
                .param(name)
                .query(Person.class)
                .optional();
    }

    public List<Book> personListBooks(int personId) {
        return jdbcClient.sql("SELECT * FROM BOOK WHERE person_id = ?")
                .param(personId)
                .query(Book.class)
                .list();

    }

}
