package com.rabulinski.bookorder.order;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequest {
    private UUID bookId;
    private String bookName;
    private Integer numOfVisits;
}
