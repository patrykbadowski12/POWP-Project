package com.studies.Library;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.studies.Library.entity.Book;
import com.studies.Library.entity.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Service
public class BookService implements Observable {

    private Set<Observer> observers = new HashSet<>();
    private Book newestBook;

    @Autowired
    private BookRepository bookRepository;

    private final RestTemplate restTemplate;

    public BookService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public List<Book> getAllBooks() {
        URI uri = URI.create("http://localhost:8090/books");
        Book[] forObject = this.restTemplate.getForObject(uri, Book[].class);
        return Arrays.asList(forObject);
    }

    public List fallback() {
        return Collections.EMPTY_LIST;
    }

    public Book getBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public void addBook(BookDTO bookReq) {
        Book book = new Book.BookBuilder()
                .author(bookReq.getAuthor())
                .pages(bookReq.getPages())
                .print(bookReq.getPrint())
                .title(bookReq.getTitle())
                .build();
        this.newestBook = bookRepository.save(book);
        notifyObservers();
    }

    public Book updateBook(final Long id, final BookDTO bookReq) throws DataNotFoundException {
        Book book = bookRepository.findById(id).orElse(null);
        if (book!= null) {
            book.setAuthor(bookReq.getAuthor());
            book.setPages(bookReq.getPages());
            book.setPrint(bookReq.getPrint());
            book.setTitle(bookReq.getTitle());
            return bookRepository.save(book);
        } else {
            throw new DataNotFoundException(id);
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    public Book getNewestBook() {
        return this.newestBook;
    }
}
