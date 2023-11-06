package com.ra.model;

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

    public Book() {
    }

    public Book(int bookId, String bookName, float price, boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.status = status;
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
}
