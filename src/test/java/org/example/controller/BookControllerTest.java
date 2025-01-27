package org.example.controller;

import org.example.dto.BookDTO;
import org.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    private BookService bookService;
    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookService = Mockito.mock(BookService.class);
        bookController = new BookController(bookService);
    }

    @Test
    void getAllBooks() {
        // mOCK
        List<BookDTO> mockBooks = Arrays.asList(
                new BookDTO(1L, "Book", "Author", "Category", "ISBN", new Date()),
                new BookDTO(2L, "Book", "Author", "Category", "ISBN", new Date())
        );
        when(bookService.getAllBooks()).thenReturn(mockBooks);

        // test
        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBooks, response.getBody());
    }

    @Test
    void getBookById() {
        // mock
        Long bookId = 1L;
        BookDTO mockBook = new BookDTO(bookId, "Book", "Author", "Category", "ISBN", new Date());
        when(bookService.getBookById(bookId)).thenReturn(mockBook);

        // test
        ResponseEntity<BookDTO> response = bookController.getBookById(bookId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBook, response.getBody());
    }

    @Test
    void createBook() {
        // mock
        BookDTO mockBook = new BookDTO(null, "Book", "Author", "Category", "ISBN", new Date());
        BookDTO savedBook = new BookDTO(1L, "Book", "Author", "Category", "ISBN", new Date());
        when(bookService.createBook(mockBook)).thenReturn(savedBook);

        // test
        ResponseEntity<BookDTO> response = bookController.createBook(mockBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(savedBook, response.getBody());
    }

    @Test
    void updateBook() {
        Long bookId = 1L;
        BookDTO updatedBook = new BookDTO(bookId, "Updated Book", "Updated Author", "Updated Category", "ISBN-Updated", new Date());
        when(bookService.updateBook(eq(bookId), any(BookDTO.class))).thenReturn(updatedBook);

        ResponseEntity<BookDTO> response = bookController.updateBook(bookId, updatedBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedBook, response.getBody());
    }

    @Test
    void deleteBook() {
        Long bookId = 1L;

        ResponseEntity<Void> response = bookController.deleteBook(bookId);
        // 204 > Chamou o delete mas nao havia nada pra apagar
        assertEquals(204, response.getStatusCodeValue());
    }

}
