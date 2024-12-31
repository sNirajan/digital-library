package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment of id, such as 1, 2, 3
    private Long id;

    private String title;
    private String author;
    private String genre;
    private boolean availability;

    public Book(){
    }

    public Book(String title, String author, String genre, boolean availability){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    // Getters and Setters
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public boolean isAvailability(){
        return availability;
    }

    public void setAvailability(boolean availability){
        this.availability = availability;
    }
}
