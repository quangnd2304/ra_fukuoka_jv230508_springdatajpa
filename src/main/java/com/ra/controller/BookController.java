package com.ra.controller;

import com.ra.model.Book;
import com.ra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/bookController")
public class BookController {
    private static final int SIZE = 3;
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/findAll")
    public ModelAndView findAllBook(int page, String orderDir, String orderBy) {
        ModelAndView mav = new ModelAndView("books");
        //Tính tổng số sách
        int totalPage = (int) Math.ceil((double)bookService.countBook()/(double)SIZE);
        List<Integer> listPage = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            listPage.add(i+1);
        }
        //Spring Data JPA tính từ trang 0
        List<Book> listBooks = bookService.findAllAndPagging_Sorting((page-1),SIZE,orderDir,orderBy);
        mav.addObject("listPage",listPage);
        mav.addObject("listBooks", listBooks);
        return mav;
    }
    @PostMapping(value = "/sortAndPagging")
    public ModelAndView sortAndPagging(String orderDir, String orderBy){
        ModelAndView mav = new ModelAndView("books");
        //Tính tổng số sách
        int totalPage = (int) Math.ceil((double)bookService.countBook()/(double)SIZE);
        List<Integer> listPage = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            listPage.add(i+1);
        }
        List<Book> listBooks = bookService.findAllAndPagging_Sorting(0,SIZE,orderDir,orderBy);
        mav.addObject("listPage",listPage);
        mav.addObject("listBooks", listBooks);
        return mav;
    }
}
