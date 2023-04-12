package com.example.elibrary.crud_elibrary_project.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {

    private int id;
    @NotEmpty(message = "У пользователя должно быть имя!")
    @Size(max = 50)
    private String full_name;
    @NotEmpty
    @Min(1900)
    private int age;

    public Person(int id, String full_name, int age) {
        this.id = id;
        this.full_name = full_name;
        this.age = age;
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
}
