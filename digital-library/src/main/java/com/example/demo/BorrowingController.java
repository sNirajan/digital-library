package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService){
        this.borrowingService = borrowingService;
    }

    // Borrow a book
    @PostMapping
    public ResponseEntity<?> borrowBook(@RequestBody BorrowingRequest borrowingRequest) {
        try {
            Borrowing borrowing = borrowingService.borrowBook(borrowingRequest.getBookId(), borrowingRequest.getUserId());
            return new ResponseEntity<>(borrowing, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    // Return a book
    @PutMapping("/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id){
        try {
            Borrowing borrowing = borrowingService.returnBook(id);
            return new ResponseEntity<>(borrowing, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Get all borrowing records (ADMIN only)
    @GetMapping
    public ResponseEntity<List<Borrowing>> getAllBorrowings() {
        List<Borrowing> borrowings = borrowingService.getAllBorrowings();
        return new ResponseEntity<>(borrowings, HttpStatus.OK);
    }

    // Get borrowing records for a user (User-specific view)
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBorrowingByUser(@PathVariable Long userId) {
        try {
            List<Borrowing> borrowings = borrowingService.getBorrowingByUser(userId);
            return new ResponseEntity<>(borrowings, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
