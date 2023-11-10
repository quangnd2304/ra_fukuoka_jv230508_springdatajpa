package com.ra.controller;

import com.ra.model.Book;
import com.ra.service.BookService;
import com.ra.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/bookController")
public class BookController {
    private static final int SIZE = 3;
    private static String bookNameSearch = "";
    private static int pageBook=1;
    private static String directionBook="ASC";
    private static String sortByBook="bookId";
    @Autowired
    private BookService bookService;
    @Autowired
    private FileStorageService fileStorageService;


    @GetMapping(value = "/findBook")
    public ModelAndView findBook(Optional<String> bookName, Optional<Integer> page, Optional<String> direction, Optional<String> sortBy){
        ModelAndView mav = new ModelAndView("books");
        bookNameSearch = bookName.orElse(bookNameSearch);
        pageBook = page.orElse(pageBook);
        directionBook = direction.orElse(directionBook);
        sortByBook = sortBy.orElse(sortByBook);
        List<Book> listBooks = bookService.findBook(bookNameSearch,pageBook-1,SIZE,directionBook,sortByBook);
        List<Integer> listPage = bookService.countPage(bookNameSearch,SIZE);
        mav.addObject("listBooks",listBooks);
        mav.addObject("listPage",listPage);
        mav.addObject("bookName",bookNameSearch);
        mav.addObject("sortBy",sortByBook);
        mav.addObject("direction",directionBook);
        return mav;
    }
    @GetMapping(value = "/initCreate")
    public String initCreate(ModelMap modelMap){
        Book book = new Book();
        modelMap.addAttribute("book",book);
        return "newBook";
    }

    @PostMapping(value = "create")
    public String createProduct(Book book, MultipartFile bookImage, MultipartFile[] otherImages){
        String urlImage = fileStorageService.uploadFile(bookImage);
        book.setImage(urlImage);
        bookService.save(book);
        List<String> listString = new ArrayList<>();
        Arrays.asList(otherImages).forEach(img->{
            listString.add(fileStorageService.uploadFile(img));
        });
        return "redirect:findBook";
    }

}
