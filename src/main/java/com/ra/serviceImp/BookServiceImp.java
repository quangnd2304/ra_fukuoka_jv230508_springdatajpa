package com.ra.serviceImp;

import com.ra.model.Book;
import com.ra.repository.BookRepository;
import com.ra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findBook(String bookName, int page, int size, String direction, String sortBy) {
        Pageable pageable = PageRequest.of(page, size,
                direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortBy);
        return bookRepository.findByBookName(bookName, pageable).getContent();
    }

    @Override
    public List<Integer> countPage(String bookName, int size) {
        int cnt = bookRepository.countBookByBookNameContains(bookName);
        List<Integer> listPage = new ArrayList<>();
        for (int i = 0; i < (int)Math.ceil((double) cnt/(double) size); i++) {
            listPage.add(i+1);
        }
        return listPage;
    }

    @Override
    public boolean save(Book book) {
        try {
            Book bookNew = bookRepository.save(book);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
