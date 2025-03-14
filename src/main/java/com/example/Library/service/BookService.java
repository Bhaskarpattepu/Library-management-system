package com.example.Library.service;

import com.example.Library.model.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.util.DataStructureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository)
    {
        this.bookRepository=bookRepository;
    }

    private DataStructureUtil util = new DataStructureUtil();
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        util.processBookList(books);
        return books;
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        util.processGenre(book.getGenre());
        util.addBookToMap(book);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setGenre(bookDetails.getGenre());
            return bookRepository.save(book);
        } else {
            return null; // Or handle error appropriately
        }
    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public String getNextBookInQueue() {
        return util.getNextBookTitleInQueue();
    }

}
