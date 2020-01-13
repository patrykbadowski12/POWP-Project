package com.studies.Library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDetail implements Command {

    private final BookService bookService;

    @Override
    public void execute(Long id) {
        System.out.println("Book details : " + bookService.getBook(id));
    }
}
