package com.example.elibrary.crud_elibrary_project.dao;

import com.example.elibrary.crud_elibrary_project.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public Book showById(int id) {
        Optional<Book> returnBook = jdbcTemplate.queryForStream("SELECT * FROM book WHERE id=?", new BookMapper(), id).findAny();
        return returnBook.orElse(null);
    }

    public void updateBook(Book book, int id) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, age=? WHERE id=?"
                                ,book.getTitle(), book.getAuthor(), book.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, age) VALUES (?,?,?)",
                                 book.getTitle(), book.getAuthor(), book.getAge());
    }
}
