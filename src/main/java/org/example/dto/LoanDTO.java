package org.example.dto;

import org.example.model.AppUser;
import org.example.model.Book;

import java.util.Date;
public class LoanDTO {

    private Long id;
    private Long user;
    private Long book;
    private String status;
    private Date returnDate;
    private Date loanDate;
    private String bookName;

    public LoanDTO() {
    }

    public LoanDTO(Long id, Long userId, Long bookId, String status, Date returnDate, Date loanDate, String bookName) {
        this.id = id;
        this.user = userId;
        this.book = bookId;
        this.status = status;
        this.returnDate = returnDate;
        this.loanDate = loanDate;
        this.bookName = bookName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getBook() {
        return book;
    }

    public void setBook(Long book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
