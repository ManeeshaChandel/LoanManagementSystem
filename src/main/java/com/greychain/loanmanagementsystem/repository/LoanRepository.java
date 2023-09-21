package com.greychain.loanmanagementsystem.repository;

import com.greychain.loanmanagementsystem.entities.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LoanRepository  extends CrudRepository<Loan,Integer>{
    Loan findByLoanId(int id);
    Loan findByCustomer_CustomerId(int customerId);
    Loan findByLender_LenderId(int lenderid);

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
