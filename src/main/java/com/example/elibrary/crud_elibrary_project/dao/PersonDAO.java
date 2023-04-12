package com.example.elibrary.crud_elibrary_project.dao;

import com.example.elibrary.crud_elibrary_project.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person showById(int id) {
        Optional<Person> person = jdbcTemplate.queryForStream("SELECT * FROM person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id).findAny();
        return person.orElse(null);
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE person SET full_name=?, age=? WHERE id=?", person.getFull_name(), person.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, age) VALUES(?,?)", person.getFull_name(), person.getAge());
    }
}
