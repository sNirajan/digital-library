package com.digitallibrary.service;

import com.digitallibrary.model.Borrowing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BorrowingServiceTest {

    @Autowired
    private BorrowingService borrowingService;

    @Test
    void testBorrowAndReturnFlow() {
        try {
            Borrowing borrowing = borrowingService.borrowBook(1L, 1L);
            System.out.println("Borrowed: " + borrowing);

            Borrowing returned = borrowingService.returnBook(1L);
            System.out.println("Returned: " + returned);

            borrowingService.getAllBorrowings().forEach(System.out::println);
            borrowingService.getBorrowingByUser(1L).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
