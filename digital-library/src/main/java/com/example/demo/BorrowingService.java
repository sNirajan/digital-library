package com.example.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowingService(BorrowingRepository borrowingRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // Borrow a book
    public Borrowing borrowBook(Long bookId, Long userId) {

        // Check if the book exists
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with bookId: " + bookId));

        if (borrowingRepository.findByBookAndStatus(book, Borrowing.Status.BORROWED).isPresent()) {
            throw new IllegalStateException("This book is already borrowed.");
        }

        // Check if the user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with userId: "+ userId));

        long borrowedCount = borrowingRepository.countByUserAndStatus(user, Borrowing.Status.BORROWED);
        if (borrowedCount >= 3) {
            throw new IllegalStateException("You have reached the maximum borrowing limit.");
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setUser(user);
        borrowing.setStatus(Borrowing.Status.BORROWED);
        borrowing.setBorrowedDate(LocalDate.now());
        borrowing.setDueDate(LocalDate.now().plusDays(14)); // 14-day borrowing period

        return borrowingRepository.save(borrowing);
    }

    // Return a borrowed book
    public Borrowing returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow(() -> new IllegalArgumentException("Borrowing record not found with id: " + borrowingId));

        if (borrowing.getStatus() != Borrowing.Status.BORROWED) {
            throw new IllegalStateException("Book is not currently borrowed");
        }

        borrowing.setReturnDate(LocalDate.now());
        borrowing.setStatus(Borrowing.Status.RETURNED);

        return borrowingRepository.save(borrowing);
    }

    // Get all borrowing records
    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    // Get borrowing records by user
    public List<Borrowing> getBorrowingByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        return borrowingRepository.findByUser(user);
    }
}
