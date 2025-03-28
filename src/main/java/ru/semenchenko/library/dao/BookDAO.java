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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM BOOK", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE book_id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO BOOK(name, author, year_of_release) VALUES(?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYearOfRelease());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE BOOK SET name = ?, author = ?, year_of_release = ? WHERE book_id = ?",
                updatedBook.getName(),
                updatedBook.getAuthor(),
                updatedBook.getYearOfRelease(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM BOOK WHERE book_id = ?", id);
    }

    public Optional<Person> takenBy(int bookId) {
        return jdbcTemplate.query("SELECT Person.* FROM PERSON JOIN BOOK on Person.person_id = Book.person_id where book_id = ? ",
                        new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void assign(int bookId, Person person) {
        jdbcTemplate.update("UPDATE BOOK SET person_id = ? WHERE book_id = ?",
                person.getPersonId(), bookId);
    }

    public void release(int bookId) {
        jdbcTemplate.update("UPDATE BOOK SET person_id = NULL WHERE book_id = ?",
                bookId);
    }
}
