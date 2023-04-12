package com.example.elibrary.crud_elibrary_project.controllers;

import com.example.elibrary.crud_elibrary_project.dao.PersonDAO;
import com.example.elibrary.crud_elibrary_project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/create")
    public String newPerson(@ModelAttribute("person")Person person){
        return "people/create";
    }

    @PostMapping()            //todo (Доделать валидацию, пока без неё)
    public String createPerson(@ModelAttribute("person")Person person){
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("person", personDAO.showAll());
        return "people/showAll";
    }

    @GetMapping("/{id}")
    public String showPersonById(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.showById(id));
        return "person/showById";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.showById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")Person person, @PathVariable("id")int id){
        personDAO.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

}
