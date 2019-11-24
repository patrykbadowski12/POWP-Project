package com.studies.Library.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Book {

    @Id
    private long id;
    private String title;
    private String author;
    private int pages;
    private String print;
}
