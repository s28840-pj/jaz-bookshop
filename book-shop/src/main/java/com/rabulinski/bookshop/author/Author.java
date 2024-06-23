package com.rabulinski.bookshop.author;

import com.rabulinski.bookshop.book.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Author {
    @Id
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "author")
    private List<Book> book;
}