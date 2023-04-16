package com.example.elibrary.crud_elibrary_project.controllers;

import com.example.elibrary.crud_elibrary_project.dao.BookDAO;
import com.example.elibrary.crud_elibrary_project.dao.PersonDAO;
import com.example.elibrary.crud_elibrary_project.models.Book;
import com.example.elibrary.crud_elibrary_project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("/create")
    public String newBook(@ModelAttribute("book")Book book){
        return "books/create";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book")Book book){
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("books", bookDAO.showAll());
        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String showBookById(@PathVariable("id")int id, Model model, @ModelAttribute("person")Person person){
        Book book = bookDAO.showById(id);
        if(book.getHolder() != 0) {
            Person holder = personDAO.showById(book.getHolder());
            model.addAttribute("holder", holder);
        }
        model.addAttribute("people", personDAO.showAll());
        model.addAttribute("book", book);
        return "books/showById";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id, Model model){
        model.addAttribute("book", bookDAO.showById(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id")int id, @ModelAttribute("book")Book book){
        bookDAO.updateBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/rid_book")
    public String removeHolder(@PathVariable("id")int id){
        bookDAO.rid_book(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/appoint_holder")
    public String appointHolder(@PathVariable("id")int id, @ModelAttribute("person")Person person){
        bookDAO.appoint_holder(id, person.getId());
        return "redirect:/books/{id}";
    }
}
