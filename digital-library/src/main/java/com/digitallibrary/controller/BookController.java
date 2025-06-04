package com.digitallibrary.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.digitallibrary.model.Book;
import com.digitallibrary.repository.BookRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // 1. Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 2. Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Add a new book
    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    // 4. Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setGenre(bookDetails.getGenre());
            book.setAvailability(bookDetails.isAvailability());
            Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(updatedBook);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        return bookRepository.findById(id).map(book -> {
            bookRepository.delete(book);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // Get paginated and sorted books
    @GetMapping("/paginated")
    public Page<Book> getPaginatedBooks(
            @RequestParam(defaultValue = "0") int page, // Page number (default: 0)
            @RequestParam(defaultValue = "10") int size, // Page size (default: 10)
            @RequestParam(defaultValue = "title") String sortBy // Sort by field (default: "title")
    ) {
        return bookRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword){
        return bookRepository.searchBooks(keyword);
    }
}

