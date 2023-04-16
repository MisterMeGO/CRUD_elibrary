package com.example.elibrary.crud_elibrary_project.models;

import com.example.elibrary.crud_elibrary_project.dao.PersonDAO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Person {

    private int id;
    @NotEmpty(message = "У пользователя должно быть имя!")
    @Size(max = 50)
    private String full_name;
    @NotEmpty
    @Min(1900)
    private int age;

    @Autowired
    private PersonDAO personDAO;

    private List<Book> books;

    public Person(int id, String full_name, int age) {
        this.id = id;
        this.full_name = full_name;
        this.age = age;
        this.books = personDAO.getBooksPerPerson(this);
    }

    public Person() {
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }
}
