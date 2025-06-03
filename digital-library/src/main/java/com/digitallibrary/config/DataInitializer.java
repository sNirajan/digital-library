package com.digitallibrary.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.digitallibrary.model.Book;
import com.digitallibrary.repository.BookRepository;

@Component
public class DataInitializer {
    @Bean
    CommandLineRunner init(BookRepository bookRepository){
        return args -> {
            bookRepository.save(new Book("Atomic Habits", "James Clear", "Self-help", true));
            bookRepository.save(new Book("Ego Is The Enemy", "Ryan Holiday", "Self-help", true));

            System.out.println("\nBooks initialized in database");

            bookRepository.findAll().forEach(book -> System.out.println(book.getTitle()));
        };
    }
}


/*
CommandLineRunner

What It Does:
CommandLineRunner is an interface that Spring Boot runs after the application has started.
It allows us to execute code (e.g., inserting data) when the application starts.

Why It’s Needed:
We used it to populate sample books in the database during application startup.

How It Works:
The DataInitializer class implements CommandLineRunner via the @Bean method,
and Spring executes the code inside init() once the application is running.
 */