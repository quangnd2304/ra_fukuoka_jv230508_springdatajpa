package com.ra.repository;

import com.ra.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b where b.bookName like %?1%")
    Page<Book> findByBookName(String bookName, Pageable pageable);
    int countBookByBookNameContains(String bookName);
}
