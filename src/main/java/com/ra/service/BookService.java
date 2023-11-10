package com.ra.service;

import com.ra.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findBook(String bookName, int page, int size, String direction, String sortBy);
    List<Integer> countPage(String bookName,int size);
    boolean save(Book book);
}
