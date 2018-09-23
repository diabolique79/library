package pl.homework.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.homework.library.model.Book;
import pl.homework.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private BookRepository bookRepository;


    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/")
    public String homePage(Model model) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("ksiazki", books);
        return "home";
    }

    @GetMapping("/ksiazka/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        model.addAttribute("ksiazka", optionalBook.get());
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
    public String deleteBook(@RequestParam Long id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edytowanie")
    public String updateBookForm(@RequestParam Long id, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        model.addAttribute("ksiazka", optionalBook.get());
        return "edytowanie";
    }


    @PostMapping("/edytowanie")
    public String updateBookPost(@RequestParam Long id) {
        bookRepository.findById(id);
        return "bo";
    }
}
