package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Optional<Borrowing> findByBookAndStatus(Book book, Borrowing.Status status);
    List<Borrowing> findByUser(User user);
    long countByUserAndStatus(User user, Borrowing.Status status);
}
