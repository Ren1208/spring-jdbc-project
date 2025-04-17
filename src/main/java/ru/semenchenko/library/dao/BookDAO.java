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
public class BookDAO {
    private final JdbcClient jdbcClient;

    @Autowired
    public BookDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Book> index() {
        return jdbcClient.sql("SELECT * FROM BOOK")
                .query(Book.class)
                .list();
    }

    public Book show(int id) {
        return jdbcClient.sql("SELECT * FROM BOOK WHERE book_id = ?")
                .param(id)
                .query(Book.class)
                .single();
    }

    public void save(Book book) {
        jdbcClient.sql("INSERT INTO BOOK(name, author, year_of_release) VALUES(?, ?, ?)")
                .param(book.getName())
                .param(book.getAuthor())
                .param(book.getYearOfRelease())
                .update();
    }

    public void update(int id, Book updatedBook) {
        jdbcClient.sql("UPDATE BOOK SET name = ?, author = ?, year_of_release = ? WHERE book_id = ?")
                .param(updatedBook.getName())
                .param(updatedBook.getAuthor())
                .param(updatedBook.getYearOfRelease())
                .param(id)
                .update();
    }

    public void delete(int id) {
        jdbcClient.sql("DELETE FROM BOOK WHERE book_id = ?")
                .param(id)
                .update();
    }

    public Optional<Person> takenBy(int bookId) {
        return jdbcClient.sql("SELECT Person.* FROM PERSON JOIN BOOK on Person.person_id = Book.person_id where book_id = ?")
                .param(bookId)
                .query(Person.class)
                .optional();
    }

    public void assign(int bookId, Person person) {
        jdbcClient.sql("UPDATE BOOK SET person_id = ? WHERE book_id = ?")
                .param(person.getPersonId())
                .param(bookId)
                .update();
    }

    public void release(int bookId) {
        jdbcClient.sql("UPDATE BOOK SET person_id = NULL WHERE book_id = ?")
                .param(bookId)
                .update();
    }
}
