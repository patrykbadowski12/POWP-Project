package com.studies.Library;

import com.studies.Library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@RequestParam("id") Long id){
        Book book = bookService.getBook(id);
        if(book != null){
            return ResponseEntity.ok(book);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book")
    public ResponseEntity addBook(@RequestBody Book book){
        bookService.addBook(book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBook(@RequestParam("id") Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
