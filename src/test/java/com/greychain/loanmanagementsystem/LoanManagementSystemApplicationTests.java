package com.greychain.loanmanagementsystem;

import com.greychain.loanmanagementsystem.helper.AggregateResultDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import com.greychain.loanmanagementsystem.controller.LoanController;
import com.greychain.loanmanagementsystem.entities.Loan;
import com.greychain.loanmanagementsystem.service.LoanService;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class LoanManagementSystemApplicationTests {

    @Mock
    private LoanService loanService;

    private LoanController loanController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loanController = new LoanController(loanService);
    }

    @Test
    public void testGetAllLoansWithEmptyList() {
        List<Loan> emptyList = new ArrayList<>();
        when(loanService.getAllLoans()).thenReturn(emptyList);

        ResponseEntity<List<Loan>> responseEntity = loanController.getAllLoans();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllLoansWithNonEmptyList() {
        List<Loan> loanList = new ArrayList<>();
        loanList.add(new Loan());
        when(loanService.getAllLoans()).thenReturn(loanList);

        ResponseEntity<List<Loan>> responseEntity = loanController.getAllLoans();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(loanList, responseEntity.getBody());
    }
    @Test
    public void testAddLoanSuccess() {
        Loan loan = new Loan();
        when(loanService.addLoan(any(Loan.class))).thenReturn(loan);

        ResponseEntity<Loan> response = loanController.addLoan(loan);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    @Test
    public void testAddLoanInternalServerError() {
        Loan loan = new Loan();
        when(loanService.addLoan(any(Loan.class))).thenThrow(Exception.class);

        ResponseEntity<Loan> response = loanController.addLoan(loan);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetLoanByLoanIdFound() {
        int loanId = 1;
        when(loanService.getLoanByLoanId(loanId)).thenReturn(new Loan());

        ResponseEntity<Loan> response = loanController.getLoanByLoanId(loanId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testGetLoanByLoanIdNotFound() {
        int loanId = 1;
        when(loanService.getLoanByLoanId(loanId)).thenReturn(null);

        ResponseEntity<Loan> response = loanController.getLoanByLoanId(loanId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testGetLoanByCustomerIdFound() {
        int customerId = 1;
        when(loanService.getLoanByCustomerId(customerId)).thenReturn(new Loan());

        ResponseEntity<Loan> response = loanController.getLoanByCustomerId(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testGetLoanByCustomerIdNotFound() {
        int customerId = 1;
        when(loanService.getLoanByCustomerId(customerId)).thenReturn(null);

        ResponseEntity<Loan> response = loanController.getLoanByCustomerId(customerId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testGetLoanByLenderIdFound() {
        int lenderId = 1;
        when(loanService.getLoanByLenderId(lenderId)).thenReturn(new Loan());

        ResponseEntity<Loan> response = loanController.getLoanByLenderId(lenderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testGetLoanByLenderIdNotFound() {
        int lenderId = 1;
        when(loanService.getLoanByLenderId(lenderId)).thenReturn(null);

        ResponseEntity<Loan> response = loanController.getLoanByLenderId(lenderId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testGetAggregateByLenderSuccess() {
        int lenderId = 1;
        AggregateResultDTO mockResult = new AggregateResultDTO();
        when(loanService.getAggregateByLender(lenderId)).thenReturn(mockResult);

        ResponseEntity<AggregateResultDTO> response = loanController.getAggregateByLender(lenderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResult, response.getBody());
    }
    @Test
    public void testGetAggregateByLenderException() {
        int lenderId = 1;
        when(loanService.getAggregateByLender(lenderId)).thenThrow(new RuntimeException("Some error"));

        ResponseEntity<AggregateResultDTO> response = loanController.getAggregateByLender(lenderId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    public void testGetAggregateByCustomerSuccess() {
        int customerId = 1;
        AggregateResultDTO mockResult = new AggregateResultDTO();
        when(loanService.getAggregateByCustomer(customerId)).thenReturn(mockResult);

        ResponseEntity<AggregateResultDTO> response = loanController.getAggregateByCustomer(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResult, response.getBody());
    }
    @Test
    public void testGetAggregateByCustomerException() {
        int customerId = 1;
        when(loanService.getAggregateByCustomer(customerId)).thenThrow(new RuntimeException("Some error"));

        ResponseEntity<AggregateResultDTO> response = loanController.getAggregateByCustomer(customerId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    public void testGetAggregateByInterestSuccess() {
        double interestRate = 0.05;
        AggregateResultDTO mockResult = new AggregateResultDTO();
        when(loanService.getAggregateByInterest(interestRate)).thenReturn(mockResult);

        ResponseEntity<AggregateResultDTO> response = loanController.getAggregateByInterest(interestRate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResult, response.getBody());
    }
    @Test
    public void testGetAggregateByInterestException() {
        double interestRate = 0.05;
        when(loanService.getAggregateByInterest(interestRate)).thenThrow(new RuntimeException("Some error"));

        ResponseEntity<AggregateResultDTO> response = loanController.getAggregateByInterest(interestRate);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}
