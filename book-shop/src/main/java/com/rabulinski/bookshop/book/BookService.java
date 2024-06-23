package com.rabulinski.bookshop.book;

import com.rabulinski.bookshop.author.Author;
import com.rabulinski.bookshop.author.AuthorNotFoundException;
import com.rabulinski.bookshop.author.AuthorRepository;
import com.rabulinski.bookshop.external.OrderClient;
import com.rabulinski.bookshop.utils.BookMapper;
import com.rabulinski.generated.model.BookCreateRequest;
import com.rabulinski.generated.model.BookOrder;
import com.rabulinski.generated.model.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final OrderClient orderClient;

    public ResponseEntity<BookResponse> addBook(BookCreateRequest bookCreateRequest) {
        validateBookCreateRequest(bookCreateRequest);

        Book book = bookMapper.mapToBook(bookCreateRequest);
        book.setAuthor(this.getAuthorFormId(bookCreateRequest.getAuthorId()));

        BookResponse body = bookMapper.mapToBookResponse(bookRepository.save(book));

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<BookResponse>> findByGenre(String genre) {
        return ResponseEntity.ok(bookRepository.findBooksByGenere(genre).stream().map(bookMapper::mapToBookResponse).toList());
    }

    public ResponseEntity<Void> deleteBook(UUID id) {
        validateBookId(id);

        bookRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<BookResponse> getBook(UUID id) {
        validateBookId(id);

        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setNumOfVisits(book.getNumOfVisits() + 1);
        bookRepository.save(book);

        BookResponse body = bookMapper.mapToBookResponse(book);

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<BookResponse>> getBooks() {
        List<BookResponse> body = bookRepository.findAll().stream().map(bookMapper::mapToBookResponse).toList();

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<BookResponse> updateBook(UUID id, BookCreateRequest bookCreateRequest) {
        validateBookId(id);
        validateBookCreateRequest(bookCreateRequest);

        Book book = bookRepository.getReferenceById(id);
        Book newBook = bookMapper.mapToBook(bookCreateRequest);
        newBook.setAuthor(this.getAuthorFormId(bookCreateRequest.getAuthorId()));
        newBook.setId(book.getId());

        BookResponse body = bookMapper.mapToBookResponse(
                bookRepository.save(newBook
                ));

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<BookOrder>> sendOrder() {
        return orderClient.sendOrder(bookRepository.findAll().stream().map(bookMapper::mapToOrderRequest).toList());
    }

    private void validateBookCreateRequest(BookCreateRequest bookCreateRequest) {
        if (bookCreateRequest == null || bookCreateRequest.getAuthorId() == null || bookCreateRequest.getAvailableCopies() == null || bookCreateRequest.getGenre() == null || bookCreateRequest.getPrice() == null || bookCreateRequest.getNumOfPages() == null)
            throw new InvalidBookCreateRequestException();
    }

    private void validateBookId(UUID uuid) {
        if (!bookRepository.existsById(uuid))
            throw new BookNotFoundException();
    }

    private Author getAuthorFormId(UUID uuid) {
        return authorRepository.findById(uuid).orElseThrow(AuthorNotFoundException::new);
    }
}
