package org.example.service;

import org.example.dto.BookDTO;
import org.example.model.Book;
import org.example.repo.IBookRepository;
import org.example.repo.ILoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private IBookRepository bookRepository;

    @Mock
    private ILoanRepository loanRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //De acordo com o PDF, essa deveria ser a principal funcionalidade (Talvez a unica)
    //visto que o restante é CRUD basico ----------------------------------------------
    //e como eu testei os controllers, este será o unico Service que vou testar, por questão
    //de tempo

    @Test
    void testRecommendBooks_WhenUserHasLoans() {
        // Aqui eu estou fazendo o mock para o teste
        Long userId = 1L;
        List<String> categories = Arrays.asList("Ficção", "Mistério");
        when(loanRepository.findCategoriesLoanedByUser(userId)).thenReturn(categories);

        List<Book> recommendedBooks = Arrays.asList(
                new Book(1L, "Livro 1", "Autor 1", "Ficção", "123456", null),
                new Book(2L, "Livro 2", "Autor 2", "Mistério", "654321", null)
        );
        when(bookRepository.findBooksByCategoryNotLoanedByUser(categories, userId)).thenReturn(recommendedBooks);

        // Aqui é efetivamente o teste
        List<BookDTO> result = bookService.recommendBooks(userId);

        assertEquals(2, result.size());
        assertEquals("Livro 1", result.get(0).getTitle());
        assertEquals("Livro 2", result.get(1).getTitle());
    }

    @Test
    void testRecommendBooks_WhenUserHasNoLoans() {
        // Mocando
        Long userId = 1L;
        when(loanRepository.findCategoriesLoanedByUser(userId)).thenReturn(Collections.emptyList());

        when(bookRepository.findBooksByCategoryNotLoanedByUser(Collections.emptyList(), userId))
                .thenReturn(Collections.emptyList());

        // Testando
        List<BookDTO> result = bookService.recommendBooks(userId);

        assertEquals(0, result.size());
    }


    //Os testes abaixos são bem simples, feitos apenas para demonstrar

    @Test
    void testGetBookById_BookExists() {
        Long bookId = 1L;
        Book book = new Book(1L, "Livro", "Autor Exemplo", "Ficção", "12345", null);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookDTO result = bookService.getBookById(bookId);

        assertEquals("Livro", result.getTitle());
    }

    @Test
    void testGetBookById_BookDoesNotExist() {
        Long bookId = 99L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.getBookById(bookId));
    }
}
