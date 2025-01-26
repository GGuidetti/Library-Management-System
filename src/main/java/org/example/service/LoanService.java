package org.example.service;

import org.example.dto.LoanDTO;
import org.example.mapper.AppUserMapper;
import org.example.mapper.BookMapper;
import org.example.repo.ILoanRepository;
import org.example.model.Loan;
import org.example.model.AppUser;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final ILoanRepository loanRepository;
    private final BookService bookService;
    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;
    private final BookMapper bookMapper;

    @Autowired
    public LoanService(ILoanRepository loanRepository, BookService bookService,
                       AppUserService appUserService, AppUserMapper appUserMapper,
                       BookMapper bookMapper) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
        this.bookMapper = bookMapper;
    }

    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();

        return loans.stream()
                .map(loan -> {
                    Book book = loan.getBook();
                    String bookName = (book != null) ? book.getTitle() : null;

                    return new LoanDTO(
                            loan.getId(),
                            loan.getUser().getId(),
                            loan.getBook() != null ? loan.getBook().getId() : null,
                            loan.getStatus(),
                            loan.getReturnDate(),
                            loan.getLoanDate(),
                            bookName
                    );
                })
                .collect(Collectors.toList());
    }


    public LoanDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado com ID: " + id));
        return new LoanDTO(loan.getId(), loan.getUser().getId(), loan.getBook().getId(), loan.getStatus(), loan.getReturnDate(), loan.getLoanDate(), loan.getBook() != null ? loan.getBook().getTitle() : null);
    }

    public List<LoanDTO> getLoansByUserId(Long userId) {
        List<Loan> loans = loanRepository.findAllByUserId(userId);

        return loans.stream()
                .map(loan -> {
                    Book book = loan.getBook();
                    String bookName = (book != null) ? book.getTitle() : null;

                    return new LoanDTO(
                            loan.getId(),
                            loan.getUser().getId(),
                            loan.getBook() != null ? loan.getBook().getId() : null,
                            loan.getStatus(),
                            loan.getReturnDate(),
                            loan.getLoanDate(),
                            bookName
                    );
                })
                .collect(Collectors.toList());
    }

    public Loan getLoanByBook(long bookId) {
        return loanRepository.findByBook(bookId)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado com o titulo: " + bookId));
    }

    public LoanDTO createLoan(LoanDTO loanDTO) {
        AppUser user = appUserMapper.toEntity(appUserService.getAppUserById(loanDTO.getUser()));
        Book book = bookMapper.toEntity(bookService.getBookById(loanDTO.getBook()));

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setStatus(loanDTO.getStatus());
        loan.setUser(user);
        loan.setReturnDate(loanDTO.getReturnDate());
        loan.setLoanDate(loanDTO.getLoanDate());

        Loan savedLoan = loanRepository.save(loan);
        return new LoanDTO(savedLoan.getId(), savedLoan.getUser().getId(), savedLoan.getBook().getId(), savedLoan.getStatus(), savedLoan.getReturnDate(), savedLoan.getLoanDate(), savedLoan.getBook() != null ? savedLoan.getBook().getTitle() : null);
    }

    public LoanDTO updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com ID: " + id));

        AppUser user = appUserMapper.toEntity(appUserService.getAppUserById(loanDTO.getUser()));
        Book book = bookMapper.toEntity(bookService.getBookById(loanDTO.getBook()));

        loan.setBook(book);
        loan.setStatus(loanDTO.getStatus());
        loan.setUser(user);
        loan.setReturnDate(loanDTO.getReturnDate());
        loan.setLoanDate(loanDTO.getLoanDate());
        Loan updatedLoan = loanRepository.save(loan);

        return new LoanDTO(updatedLoan.getId(), updatedLoan.getUser().getId(), updatedLoan.getBook().getId(), updatedLoan.getStatus(), updatedLoan.getReturnDate(), updatedLoan.getLoanDate(), updatedLoan.getBook() != null ? updatedLoan.getBook().getTitle() : null);
    }
    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new RuntimeException("Loan não encontrado com ID: " + id);
        }
        loanRepository.deleteById(id);
    }
}
