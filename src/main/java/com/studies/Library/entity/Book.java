package com.studies.Library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String title;
    private String author;
    private int pages;
    private String print;

    public static final class BookBuilder {
        private String title;
        private String author;
        private int pages;
        private String print;

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder pages(int pages) {
            this.pages = pages;
            return this;
        }

        public BookBuilder print(String print) {
            this.print = print;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.author = this.author;
            book.pages = this.pages;
            book.print = this.print;
            book.title = this.title;
            return book;
        }
    }
}
