package com.studies.Library;

import com.studies.Library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BooksCommands booksCommands;

    @GetMapping("/observers")
    public void createAndAttachObserver() {
        bookService.attach(new Reader(bookService));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id){
        Book book = bookService.getBook(id);
        if(book != null){
            return ResponseEntity.ok(book);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book")
    public ResponseEntity addBook(@RequestBody BookDTO bookReq) {
        bookService.addBook(bookReq);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity updateBook(@PathVariable final Long id, @RequestBody BookDTO bookReq) {
        try {
            bookService.updateBook(id, bookReq);
            return ResponseEntity.ok().build();
        } catch (DataNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/commands-init")
    public ResponseEntity initCommands() {
        booksCommands.addCommand(new BorrowBook(bookService));
        booksCommands.addCommand(new GetDetail(bookService));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/command-execute/{id}")
    public ResponseEntity executeCommands(@PathVariable Long id) {
        booksCommands.executeCommands(id);
        return ResponseEntity.ok().build();
    }

}
