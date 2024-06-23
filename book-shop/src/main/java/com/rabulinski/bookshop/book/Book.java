package com.rabulinski.bookshop.book;

import com.rabulinski.bookshop.author.Author;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class Book {
    @Id
    @UuidGenerator
    private UUID id;
    private String genre;
    private String bookName;
    private Double price;
    private Integer numOfPages;
    private Integer numOfVisits = 0;
    private Integer availibleCopies;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
