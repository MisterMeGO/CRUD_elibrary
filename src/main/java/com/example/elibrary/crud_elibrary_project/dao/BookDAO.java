package com.example.elibrary.crud_elibrary_project.dao;

import com.example.elibrary.crud_elibrary_project.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> showById(int id) {
        return jdbcTemplate.queryForStream("SELECT * FROM book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id).findAny();
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
