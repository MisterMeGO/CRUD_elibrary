package com.example.elibrary.crud_elibrary_project.repositories;

import com.example.elibrary.crud_elibrary_project.models.Book;
import com.example.elibrary.crud_elibrary_project.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByHolder(Person holder);
    Book findByTitleStartingWith(String title);
}
