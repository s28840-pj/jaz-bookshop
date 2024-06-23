package com.rabulinski.bookshop.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("select b from Book b where lower(b.genre) like lower(:genere)")
    List<Book> findBooksByGenere(@Param("genere") String genere);
}
