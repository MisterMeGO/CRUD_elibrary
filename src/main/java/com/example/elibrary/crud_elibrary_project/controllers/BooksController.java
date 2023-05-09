package com.example.elibrary.crud_elibrary_project.controllers;

import com.example.elibrary.crud_elibrary_project.models.Book;
import com.example.elibrary.crud_elibrary_project.models.Person;
import com.example.elibrary.crud_elibrary_project.services.BooksService;
import com.example.elibrary.crud_elibrary_project.services.PeopleService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping("/create")
    public String newBook(@ModelAttribute("book")Book book){
        return "books/create";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book")Book book){
        booksService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping()
    public String showAll(Model model, HttpServletRequest request){

        model.addAttribute("books", booksService.findAllBooks(request));

        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String showBookById(@PathVariable("id")int id, Model model, @ModelAttribute("person")Person person){
        Book book = booksService.findOneBook(id);
        model.addAttribute("holder", book.getHolder());
        model.addAttribute("people", peopleService.findAllPeople());
        model.addAttribute("book", book);
        return "books/showById";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id, Model model){
        model.addAttribute("book", booksService.findOneBook(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id")int id, @ModelAttribute("book")Book book){
        booksService.updateBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        booksService.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/rid_book")
    public String removeHolder(@PathVariable("id")int id){
        booksService.rid_book(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/appoint_holder")
    public String appointHolder(@PathVariable("id")int id, @ModelAttribute("person")Person person){
        booksService.appoint_holder(id, person);
        return "redirect:/books/{id}";
    }

    @GetMapping("/search")
    public String searchBook(HttpServletRequest request, Model model){
        String searchString = request.getParameter("search_string");
        Book book = booksService.getBookBySearchString(searchString);
        model.addAttribute("result", book);
        return "/books/search";
    }
}
