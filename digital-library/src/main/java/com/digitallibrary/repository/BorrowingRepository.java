package com.digitallibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitallibrary.model.Book;
import com.digitallibrary.model.Borrowing;
import com.digitallibrary.model.User;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Optional<Borrowing> findByBookAndStatus(Book book, Borrowing.Status status);
    List<Borrowing> findByUser(User user);
    long countByUserAndStatus(User user, Borrowing.Status status);
}
