package com.rabulinski.bookorder.order;

import com.rabulinski.generated.model.BookOrder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @PostMapping("/sendOrder")
    ResponseEntity<List<BookOrder>> sendOrder (@RequestBody List<OrderRequest> orderRequests) {
        return service.receiveOrder(orderRequests);
    }

    @GetMapping("/print")
    public void exportOrder (HttpServletResponse response) {
        response.setContentType("application/pdf");
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        response.setHeader("Content-Disposition", "attachment; filename=pdf_"+date+".pdf");
        service.exportOrder(response);
    }

}
