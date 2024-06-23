package com.rabulinski.bookshop.book;

import com.rabulinski.generated.api.BookControllerApi;
import com.rabulinski.generated.model.BookCreateRequest;
import com.rabulinski.generated.model.BookOrder;
import com.rabulinski.generated.model.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController implements BookControllerApi {
    private final BookService service;

    @Override
    @PostMapping("/addBook")
    public ResponseEntity<BookResponse> createBook(BookCreateRequest bookCreateRequest) {
        return service.addBook(bookCreateRequest);
    }

    @Override
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        return service.deleteBook(id);
    }

    @Override
    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable UUID id) {
        return service.getBook(id);
    }

    @Override
    @GetMapping("/getBook")
    public ResponseEntity<List<BookResponse>> getBooks() {
        return service.getBooks();
    }

    @Override
    @GetMapping("/getBookByGenre/{gen}")
    public ResponseEntity<List<BookResponse>> getBookByGenre(@PathVariable String gen) {
        return service.findByGenre(gen);
    }

    @Override
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable UUID id, BookCreateRequest bookCreateRequest) {
        return service.updateBook(id, bookCreateRequest);
    }

    @Override
    @GetMapping("/orderBooks")
    public ResponseEntity<List<BookOrder>> orderBooks() {
        return service.sendOrder();
    }
}
