package com.rabulinski.bookorder.order;

import com.rabulinski.generated.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<BookOrder, UUID> {
}
