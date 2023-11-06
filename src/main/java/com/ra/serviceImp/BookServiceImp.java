package com.ra.serviceImp;

import com.ra.model.Book;
import com.ra.repository.BookRepository;
import com.ra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public List<Book> findByName(String bookName) {
        return bookRepository.findByBookNameContains(bookName);
    }

    @Override
    public boolean saveOrUpdate(Book book) {
        try {
            bookRepository.save(book);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int bookId) {
        try {
            bookRepository.delete(findById(bookId));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> findAllAndPagging(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Book> listBook = bookRepository.findAll(pageable).getContent();
        return listBook;
    }

    @Override
    public List<Book> findAllAndSorting(String sortDir, String sortBy) {
        if (sortDir.equals("bookName")) {
            if (sortBy.equals("ASC")) {
                return bookRepository.findAll(Sort.by("bookName").ascending());
            } else {
                return bookRepository.findAll(Sort.by("bookName").descending());
            }
        } else {
            if (sortBy.equals("ASC")) {
                return bookRepository.findAll(Sort.by("price").ascending());
            } else {
                return bookRepository.findAll(Sort.by("price").descending());
            }
        }
    }

    @Override
    public List<Book> findAllAndPagging_Sorting(int page, int size, String sortDir, String sortBy) {
        if (sortDir.equals("bookName")) {
            if (sortBy.equals("ASC")) {
                Pageable pageable = PageRequest.of(page, size, Sort.by("bookName").ascending());
                return bookRepository.findAll(pageable).getContent();
            } else {
                Pageable pageable = PageRequest.of(page, size, Sort.by("bookName").descending());
                return bookRepository.findAll(pageable).getContent();
            }
        } else {
            if (sortBy.equals("ASC")) {
                Pageable pageable = PageRequest.of(page, size, Sort.by("price").ascending());
                return bookRepository.findAll(pageable).getContent();
            } else {
                Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());
                return bookRepository.findAll(pageable).getContent();
            }
        }
    }

    @Override
    public long countBook() {
        return bookRepository.count();
    }
}
