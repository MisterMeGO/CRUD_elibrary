package com.example.elibrary.crud_elibrary_project.controllers;

import com.example.elibrary.crud_elibrary_project.models.Book;
import com.example.elibrary.crud_elibrary_project.models.Person;
import com.example.elibrary.crud_elibrary_project.services.BooksService;
import com.example.elibrary.crud_elibrary_project.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping("/create")
    public String newPerson(@ModelAttribute("person")Person person){
        return "people/create";
    }

    @PostMapping()            //todo (Доделать валидацию, пока без неё)
    public String createPerson(@ModelAttribute("person")Person person){
        peopleService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("people", peopleService.findAllPeople());
        return "people/showAll";
    }

    @GetMapping("/{id}")
    public String showPersonById(@PathVariable("id") int id, Model model){
        Person person = peopleService.findOnePerson(id);
        model.addAttribute("person", person);
        model.addAttribute("books", booksService.findAllBooksPerPerson(person));
        return "people/showById";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOnePerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")Person person, @PathVariable("id")int id){
        peopleService.updatePerson(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        peopleService.deletePerson(id);
        return "redirect:/people";
    }

}
