package com.rabulinski.bookshop.utils;

import com.rabulinski.bookorder.order.OrderRequest;
import com.rabulinski.bookshop.book.Book;
import com.rabulinski.generated.model.BookCreateRequest;
import com.rabulinski.generated.model.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse mapToBookResponse(Book book);

    BookResponse mapToBookResponse(BookCreateRequest bookCreateRequest);

    Book mapToBook(BookCreateRequest bookCreateRequest);

    Book mapToBook(BookResponse bookResponse);

    BookCreateRequest mapToBookCreateRequest(Book bookDto);

    BookCreateRequest mapToBookCreateRequest(BookResponse bookResponse);

    @Mapping(target = "bookId", expression = "java(bookDto.getId())")
    OrderRequest mapToOrderRequest(Book bookDto);
}
