package com.example.elibrary.crud_elibrary_project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "У книги должно быть название!")
    @Size(max = 50)
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "У книги должен быть автор!")
    @Size(max = 50)
    @Column(name = "author")
    private String author;
    @NotNull
    @Column(name = "age")
    private int age;

    @ManyToOne()
    @JoinColumn(name = "holder", referencedColumnName = "id")
    private Person holder;

    @Column(name = "issue_date_book")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDateBook;

    @Transient
    private boolean overdue;

    public Book(int id, String title, String author, int age) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getHolder() {
        return holder;
    }

    public void setHolder(Person holder) {
        this.holder = holder;
    }

    public Date getIssueDateBook() {
        return issueDateBook;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public void setIssueDateBook(Date issueDateBook) {
        this.issueDateBook = issueDateBook;
    }
}
