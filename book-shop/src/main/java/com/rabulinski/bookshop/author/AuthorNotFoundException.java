package com.rabulinski.bookshop.author;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) { super(message); }
    public AuthorNotFoundException() { super(); }
}
