package com.bookstore.eagle.model;

import jakarta.persistence.Entity;

@Entity
public class Book extends Product {

    private String author;

    private String publisher;

    private Genre genre;

    private AgeRating rating;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public AgeRating getRating() {
        return rating;
    }

    public void setRating(AgeRating rating) {
        this.rating = rating;
    }

}
