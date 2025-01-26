package org.example.service;

import org.example.dto.BookDTO;
import org.example.repo.IBookRepository;
import org.example.repo.ILoanRepository;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final IBookRepository bookRepository;
    private final ILoanRepository loanRepository;

    @Autowired
    public BookService(IBookRepository bookRepository, ILoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getCategory(), book.getAuthor(), book.getIsbn(), book.getPublishDate()))
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
        return new BookDTO(book.getId(), book.getTitle(), book.getCategory(), book.getAuthor(), book.getIsbn(), book.getPublishDate());
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o titulo: " + title));
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublishDate(bookDTO.getPublishDate());

        Book savedBook = bookRepository.save(book);
        return new BookDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getCategory(), savedBook.getAuthor(), savedBook.getIsbn(), savedBook.getPublishDate());
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublishDate(bookDTO.getPublishDate());
        Book updatedBook = bookRepository.save(book);
        return new BookDTO(updatedBook.getId(), updatedBook.getTitle(), updatedBook.getCategory(), updatedBook.getAuthor(), updatedBook.getIsbn(), updatedBook.getPublishDate());
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<BookDTO> recommendBooks(Long userId) {
        List<String> categories = loanRepository.findCategoriesLoanedByUser(userId);

        List<Book> recommendedBooks = bookRepository.findBooksByCategoryNotLoanedByUser(categories, userId);

        return recommendedBooks.stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getIsbn(), book.getPublishDate()))
                .collect(Collectors.toList());
    }
}
