package com.studies.Library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowBook implements Command {

    private final BookService bookService;

    @Override
    public void execute(Long id) {
        System.out.println("You borrow a book " + bookService.getBook(id));
    }
}
