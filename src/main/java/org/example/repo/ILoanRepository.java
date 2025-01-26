package org.example.repo;

import org.example.model.Loan;
import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByBook(long bookId);

    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId")
    List<Loan> findAllByUserId(long userId);

    @Query("SELECT DISTINCT b.category FROM Loan l JOIN l.book b WHERE l.user.id = :userId")
    List<String> findCategoriesLoanedByUser(long userId);
}
