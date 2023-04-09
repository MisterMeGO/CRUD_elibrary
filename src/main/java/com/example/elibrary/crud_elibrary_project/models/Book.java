package com.example.elibrary.crud_elibrary_project.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    @NotEmpty(message = "У книги должно быть название!")
    @Size(max = 50)
    private String title;
    @NotEmpty(message = "У книги должен быть автор!")
    @Size(max = 50)
    private String author;
    @NotEmpty
    private int age;

    public Book(String title, String author, int age) {
        this.title = title;
        this.author = author;
        this.age = age;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
