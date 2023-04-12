package com.example.elibrary.crud_elibrary_project.dao;

import com.example.elibrary.crud_elibrary_project.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt(1));
        book.setHolder(rs.getInt(2));
        book.setTitle(rs.getString(3));
        book.setAuthor(rs.getString(4));
        book.setAge(rs.getInt(5));
        return book;
    }
}
