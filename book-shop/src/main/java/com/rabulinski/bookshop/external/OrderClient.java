package com.rabulinski.bookshop.external;

import com.rabulinski.bookorder.order.OrderRequest;
import com.rabulinski.generated.model.BookOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "order",  url = "localhost:8080")
public interface OrderClient {
    @PostMapping("/order/sendOrder")
    ResponseEntity<List<BookOrder>> sendOrder (List<OrderRequest> orderRequests);
}
