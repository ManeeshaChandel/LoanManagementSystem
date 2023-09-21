package com.greychain.loanmanagementsystem.repository;

import com.greychain.loanmanagementsystem.entities.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LoanRepository  extends CrudRepository<Loan,Integer>{
    // Find loan by loan ID
    Loan findByLoanId(int id);

    // Find loan by customer ID
    Loan findByCustomerId(int id);

    // Find loan by lender ID
    Loan findByLenderId(int id);
    // Lender: Calculate total remaining amount, interest, and penalty
    @Query("SELECT SUM(l.remainingAmount) FROM Loan l WHERE l.lender.lenderId = :lenderId")
    double calculateTotalRemainingAmountByLender(@Param("lenderId") int lenderId);

    @Query("SELECT SUM(l.interestPerDay) FROM Loan l WHERE l.lender.lenderId = :lenderId")
    double calculateTotalInterestByLender(@Param("lenderId") int lenderId);

    @Query("SELECT SUM(l.penaltyPerDay) FROM Loan l WHERE l.lender.lenderId = :lenderId")
    double calculateTotalPenaltyByLender(@Param("lenderId") int lenderId);

    // Customer: Calculate total remaining amount, interest, and penalty
    @Query("SELECT SUM(l.remainingAmount) FROM Loan l WHERE l.customer.customerId = :customerId")
    double calculateTotalRemainingAmountByCustomer(@Param("customerId") int customerId);

    @Query("SELECT SUM(l.interestPerDay) FROM Loan l WHERE l.customer.customerId = :customerId")
    double calculateTotalInterestByCustomer(@Param("customerId") int customerId);

    @Query("SELECT SUM(l.penaltyPerDay) FROM Loan l WHERE l.customer.customerId = :customerId")
    double calculateTotalPenaltyByCustomer(@Param("customerId") int customerId);

    // Interest Rate: Calculate total remaining amount, interest, and penalty
    @Query("SELECT SUM(l.remainingAmount) FROM Loan l WHERE l.interestPerDay = :interestRate")
    double calculateTotalRemainingAmountByInterest(@Param("interestRate") double interestRate);

    @Query("SELECT SUM(l.interestPerDay) FROM Loan l WHERE l.interestPerDay = :interestRate")
    double calculateTotalInterestByInterest(@Param("interestRate") double interestRate);

    @Query("SELECT SUM(l.penaltyPerDay) FROM Loan l WHERE l.interestPerDay = :interestRate")
    double calculateTotalPenaltyByInterest(@Param("interestRate") double interestRate);


}
