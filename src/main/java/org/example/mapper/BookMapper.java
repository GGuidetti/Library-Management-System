package org.example.mapper;

import org.example.dto.BookDTO;
import org.example.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public static BookDTO toBookDTO(Book book) {
        if (book == null) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublishDate(book.getPublishDate());

        return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublishDate(bookDTO.getPublishDate());

        return book;
    }
}
