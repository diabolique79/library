package pl.homework.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.homework.library.model.Book;
import pl.homework.library.repository.BookRepository;

import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;


    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/")
    public String homePage(Model model){
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("ksiazki", books);
        return "home";
    }
    @GetMapping("/ksiazka/{id}")
    public String bookDetails(@PathVariable Long id, Model model){
        Book book = (Book) bookRepository.findAllBy(id);
        model.addAttribute("ksiazka", book);
        return "book";
    }

    @GetMapping("/dodawanie")
    public String addNewBookForm(Model model) {
        model.addAttribute("ksiazka", new Book());
        return "dodawanie";
    }

    @PostMapping("/dodawanie")
    public String addNewBook(Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/usuwanie")
    public String deleteBook(Book book){
        bookRepository.delete(book);
        return "redirect:/";
    }
}
