package com.example.demo;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class BorrowingTestRunner {

    private final BorrowingService borrowingService;

    public BorrowingTestRunner(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @Bean
    CommandLineRunner testBorrowingService() {
        return args -> {
            try {
                // Test Borrowing a Book
                System.out.println("Borrowing book with ID 1...");
                Borrowing borrowing = borrowingService.borrowBook(1L, 1L);
                System.out.println("Borrowed: "  + borrowing);

                // Test Returning a book
                System.out.println("Returning book with borrowing ID 1...");
                Borrowing returnedBorrowing  = borrowingService.returnBook(1L);
                System.out.println("Returned: " + returnedBorrowing);

                // Test Fetching all Borrowed books
                System.out.println("Fetching all borrowing records...");
                borrowingService.getAllBorrowings().forEach(System.out::println);

                // Test Fetching Borrowing Records for a User
                System.out.println("Fetching burrowing records for user with ID 1...");
                borrowingService.getBorrowingByUser(1L).forEach(System.out::println);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        };
    }
}
