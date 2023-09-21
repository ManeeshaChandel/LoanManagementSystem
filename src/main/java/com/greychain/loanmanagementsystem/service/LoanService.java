package com.greychain.loanmanagementsystem.service;

import com.greychain.loanmanagementsystem.entities.Loan;
import com.greychain.loanmanagementsystem.helper.AggregateResultDTO;
import com.greychain.loanmanagementsystem.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
    public List<Loan> getAllLoans(){
        //return list;
        List<Loan> list=(List<Loan>)this.loanRepository.findAll();
        return list;
    }
    public Loan addLoan(Loan l){
        if (l.getPaymentDate().after(l.getDueDate())) {
            throw new IllegalArgumentException("Payment date cannot be greater than the due date.");
        }

        Loan result = loanRepository.save(l);

        if (result.getPaymentDate().after(result.getDueDate())) {
            logger.warn("Loan with ID {} has crossed the due date.", result.getLoanId());
        }

        return result;
    }
    public Loan getLoanByLoanId(int id){
        Loan loan=null;
        try{
            loan=this.loanRepository.findByLoanId(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return loan;
    }
    public Loan getLoanByCustomerId(int id){
        Loan loan=null;
        try{
            loan=this.loanRepository.findByCustomer_CustomerId(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return loan;
    }
    public Loan getLoanByLenderId(int id){
        Loan loan=null;
        try{
            loan=this.loanRepository.findByLender_LenderId(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return loan;
    }

    public AggregateResultDTO getAggregateByLender(int lenderId) {
        double totalRemainingAmount = loanRepository.calculateTotalRemainingAmountByLender(lenderId);
        double totalInterest = loanRepository.calculateTotalInterestByLender(lenderId);
        double totalPenalty = loanRepository.calculateTotalPenaltyByLender(lenderId);
        AggregateResultDTO resultDTO = new AggregateResultDTO();
        resultDTO.setTotalRemainingAmount(totalRemainingAmount);
        resultDTO.setTotalInterest(totalInterest);
        resultDTO.setTotalPenalty(totalPenalty);
        return resultDTO;
    }

    public AggregateResultDTO getAggregateByCustomer(int customerId) {
        double totalRemainingAmount = loanRepository.calculateTotalRemainingAmountByCustomer(customerId);
        double totalInterest = loanRepository.calculateTotalInterestByCustomer(customerId);
        double totalPenalty = loanRepository.calculateTotalPenaltyByCustomer(customerId);
        AggregateResultDTO resultDTO = new AggregateResultDTO();
        resultDTO.setTotalRemainingAmount(totalRemainingAmount);
        resultDTO.setTotalInterest(totalInterest);
        resultDTO.setTotalPenalty(totalPenalty);
        return resultDTO;
    }

    public AggregateResultDTO getAggregateByInterest(double interestRate) {
        double totalRemainingAmount = loanRepository.calculateTotalRemainingAmountByInterest(interestRate);
        double totalInterest = loanRepository.calculateTotalInterestByInterest(interestRate);
        double totalPenalty = loanRepository.calculateTotalPenaltyByInterest(interestRate);
        AggregateResultDTO resultDTO = new AggregateResultDTO();
        resultDTO.setTotalRemainingAmount(totalRemainingAmount);
        resultDTO.setTotalInterest(totalInterest);
        resultDTO.setTotalPenalty(totalPenalty);
        return resultDTO;
    }
}
