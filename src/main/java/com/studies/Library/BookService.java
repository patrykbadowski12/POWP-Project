package com.studies.Library;

import com.studies.Library.entity.Book;
import com.studies.Library.entity.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

        public List<Book> getAllBooks(){
            return bookRepository.findAll();
        }

        public Book getBook(Long id){
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()){
                return book.get();
            }
            return null;
        }

        public void addBook(Book book){
            bookRepository.save(book);
        }

        public void deleteBook(Long id){
            bookRepository.deleteById(id);
        }

}
