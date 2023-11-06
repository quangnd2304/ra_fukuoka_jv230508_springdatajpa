package com.ra.service;

import com.ra.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(int bookId);
    List<Book> findByName(String bookName);
    boolean saveOrUpdate(Book book);
    boolean delete(int bookId);
    List<Book> findAllAndPagging(int page, int size);
    List<Book> findAllAndSorting(String sortDir, String sortBy);
    List<Book> findAllAndPagging_Sorting(int page, int size, String sortDir, String sortBy);
    long countBook();
}
