package com.example.elibrary.crud_elibrary_project.services;

import com.example.elibrary.crud_elibrary_project.models.Book;
import com.example.elibrary.crud_elibrary_project.models.Person;
import com.example.elibrary.crud_elibrary_project.repositories.BooksRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Transactional
    public void saveBook(Book book){
        booksRepository.save(book);
    }


    public List<Book> findAllBooks(HttpServletRequest request){

        String sort = request.getParameter("sort_by_year");
        String page = request.getParameter("page");
        String booksPerPage = request.getParameter("books_per_page");

        boolean isSort = (sort != null && sort.equals("true"));

        if(isSort && (page == null && booksPerPage == null)){
            return booksRepository.findAll(Sort.by(Sort.Direction.DESC,"age"));
        }
        if(page != null && booksPerPage != null){
            try {
                int pageNum = Integer.parseInt(page);
                int booksPerPageNum = Integer.parseInt(booksPerPage);
                if (!isSort)
                    return booksRepository.findAll(PageRequest.of(pageNum, booksPerPageNum)).getContent();
                return booksRepository.findAll(PageRequest.of(pageNum, booksPerPageNum, Sort.Direction.DESC, "age")).getContent();
            } catch (Exception e){
                return booksRepository.findAll();
            }
        }
        return booksRepository.findAll();
    }
    public Book findOneBook(int id){
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateBook(Book updatedBook, int id){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void deleteBook(int id){
        booksRepository.deleteById(id);
    }

    public List<Book> findAllBooksPerPerson(Person person){
        List<Book> books = booksRepository.findByHolder(person);
        Date date = new Date();
        for(Book b : books){
            if(date.getTime() - b.getIssueDateBook().getTime() > 864000000)
                b.setOverdue(true);
        }
        return books;
    }

    @Transactional
    public void rid_book(int id){
        Book book = findOneBook(id);
        book.setHolder(null);
        book.setIssueDateBook(null);
    }

    @Transactional
    public void appoint_holder(int bookId, Person newHolder){
        Book book = findOneBook(bookId);
        book.setHolder(newHolder);
        book.setIssueDateBook(new Date());
    }

    public Book getBookBySearchString(String searchString){
        return booksRepository.findByTitleStartingWith(searchString);
    }
}
