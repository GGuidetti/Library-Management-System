package org.example.controller;

import org.example.dto.LoanDTO;
import org.example.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLoans() {
        List<LoanDTO> mockLoans = List.of(
                new LoanDTO(1L, 1L, 1L, "Em emprestimo", new Date(), new Date(), "Livro1"),
                new LoanDTO(2L, 2L, 2L, "Devolvido", new Date(), new Date(), "Livro2")
        );

        when(loanService.getAllLoans()).thenReturn(mockLoans);

        ResponseEntity<List<LoanDTO>> response = loanController.getAllLoans();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockLoans, response.getBody());
    }

    @Test
    void getLoanById() {
        Long loanId = 1L;
        LoanDTO mockLoan = new LoanDTO(loanId, 1L, 1L, "Em emprestimo", new Date(), new Date(), "Livro1");

        when(loanService.getLoanById(loanId)).thenReturn(mockLoan);

        ResponseEntity<LoanDTO> response = loanController.getLoanById(loanId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockLoan, response.getBody());
    }

    @Test
    void getLoansByUserId() {
        Long userId = 1L;
        List<LoanDTO> mockLoans = List.of(
                new LoanDTO(1L, userId, 1L, "Em emprestimo", new Date(), new Date(), "Livro1")
        );

        when(loanService.getLoansByUserId(userId)).thenReturn(mockLoans);

        ResponseEntity<List<LoanDTO>> response = loanController.getLoansByUserId(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockLoans, response.getBody());
    }

    @Test
    void createLoan() {
        LoanDTO mockLoan = new LoanDTO(null, 1L, 1L, "Em emprestimo", new Date(), new Date(), "Livro1");
        LoanDTO savedLoan = new LoanDTO(1L, 1L, 1L, "Em emprestimo", new Date(), new Date(), "Livro1");

        when(loanService.createLoan(mockLoan)).thenReturn(savedLoan);

        ResponseEntity<LoanDTO> response = loanController.createLoan(mockLoan);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(savedLoan, response.getBody());
    }

    @Test
    void updateLoan() {
        Long loanId = 1L;
        LoanDTO mockLoan = new LoanDTO(loanId, 1L, 1L, "Em emprestimo", new Date(), new Date(), "Livro1");
        LoanDTO updatedLoan = new LoanDTO(loanId, 2L, 1L, "Em emprestimo", new Date(), new Date(), "Livro1");

        when(loanService.updateLoan(loanId, mockLoan)).thenReturn(updatedLoan);

        ResponseEntity<LoanDTO> response = loanController.updateLoan(loanId, mockLoan);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedLoan, response.getBody());
    }

    @Test
    void deleteLoan() {
        Long loanId = 1L;

        doNothing().when(loanService).deleteLoan(loanId);

        ResponseEntity<Void> response = loanController.deleteLoan(loanId);

        assertEquals(204, response.getStatusCodeValue());
    }
}
