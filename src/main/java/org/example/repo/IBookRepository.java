package org.example.repo;

import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.category IN :categories AND b.id NOT IN " +
            "(SELECT l.book.id FROM Loan l WHERE l.user.id = :userId)")
    List<Book> findBooksByCategoryNotLoanedByUser(List<String> categories, Long userId);
}
