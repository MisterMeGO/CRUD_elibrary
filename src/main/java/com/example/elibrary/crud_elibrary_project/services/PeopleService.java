package com.example.elibrary.crud_elibrary_project.services;

import com.example.elibrary.crud_elibrary_project.models.Book;
import com.example.elibrary.crud_elibrary_project.models.Person;
import com.example.elibrary.crud_elibrary_project.repositories.PeopleRepository;
import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;


    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void savePerson(Person person){
        peopleRepository.save(person);
    }

    public List<Person> findAllPeople(){
        return peopleRepository.findAll(Sort.by("id"));
    }

    public Person findOnePerson(int id){
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updatePerson(Person updatedPerson, int id){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void deletePerson(int id){
        peopleRepository.deleteById(id);
    }


}
