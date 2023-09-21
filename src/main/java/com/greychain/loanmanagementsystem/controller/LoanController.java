package com.greychain.loanmanagementsystem.controller;

import com.greychain.loanmanagementsystem.entities.Loan;
import com.greychain.loanmanagementsystem.helper.AggregateResultDTO;
import com.greychain.loanmanagementsystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping("")
    public ResponseEntity<List<Loan>> getAllLoans(){
        List<Loan> list=loanService.getAllLoans();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            //build function will create new ResponseEntity object
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @PostMapping("/add")
    public ResponseEntity<Loan> addLoan(@RequestBody Loan loan){
        Loan l=null;
        try{
            l=this.loanService.addLoan(loan);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanByLoanId(@PathVariable("id") int id){
        Loan loan =loanService.getLoanByLoanId(id);
        if(loan==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.of(Optional.of(loan));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Loan> getLoanByCustomerId(@PathVariable("id") int id){
        Loan loan =loanService.getLoanByCustomerId(id);
        if(loan==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.of(Optional.of(loan));
    }
    @GetMapping("/{lenderId}")
    public ResponseEntity<Loan> getLoanByLenderId(@PathVariable("id") int id){
        Loan loan =loanService.getLoanByLenderId(id);
        if(loan==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.of(Optional.of(loan));
    }

    @GetMapping("aggregate/lender/{lenderId}")
    public ResponseEntity<AggregateResultDTO> getAggregateByLender(@PathVariable int lenderId) {
        try {
            AggregateResultDTO resultDTO = loanService.getAggregateByLender(lenderId);
            return ResponseEntity.ok(resultDTO);
        } catch (Exception e) {
            // Handle exceptions and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("aggregate/customer/{customerId}")
    public ResponseEntity<AggregateResultDTO> getAggregateByCustomer(@PathVariable int customerId) {
        try {
            AggregateResultDTO resultDTO = loanService.getAggregateByCustomer(customerId);
            return ResponseEntity.ok(resultDTO);
        } catch (Exception e) {
            // Handle exceptions and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("aggregate/interest/{interestRate}")
    public ResponseEntity<AggregateResultDTO> getAggregateByInterest(@PathVariable double interestRate) {
        try {
            AggregateResultDTO resultDTO = loanService.getAggregateByInterest(interestRate);
            return ResponseEntity.ok(resultDTO);
        } catch (Exception e) {
            // Handle exceptions and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
