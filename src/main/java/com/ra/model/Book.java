package com.ra.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "price")
    private float price;
    @Column(name = "book_status")
    private boolean status;
    @Column(name = "book_image")
    private String image;

    public Book() {
    }

    public Book(int bookId, String bookName, float price, boolean status, String image) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
