package com.studies.Library;

public class DataNotFoundException extends Exception {

    public DataNotFoundException(final Long id) {
        super("Book with id [" + id + "] not found");
    }
}
