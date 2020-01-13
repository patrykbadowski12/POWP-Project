package com.studies.Library;

import com.studies.Library.entity.Book;

public class Reader implements Observer {

    private final BookService bookService;
    private Book book;

    public Reader(BookService bookService) {
        this.bookService = bookService;
        book = bookService.getNewestBook();
    }

    @Override
    public void update() {
        book = bookService.getNewestBook();
        System.out.println("Create book : " + book);
    }
}
