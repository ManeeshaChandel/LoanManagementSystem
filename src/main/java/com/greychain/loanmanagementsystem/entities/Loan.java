package com.greychain.loanmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "lender_id")
    private Lender lender;

    @Column(name = "amount")
    private double amount;

    @Column(name = "remaining_amount")
    private double remainingAmount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "interest_per_day")
    private double interestPerDay;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "penalty_per_day")
    private double penaltyPerDay;

    @Column(name = "cancel")
    private boolean cancel;

    public Long getLoanId() {

        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Lender getLender() {
        return lender;
    }

    public void setLender(Lender lender) {
        this.lender = lender;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getInterestPerDay() {
        return interestPerDay;
    }

    public void setInterestPerDay(double interestPerDay) {
        this.interestPerDay = interestPerDay;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public void setPenaltyPerDay(double penaltyPerDay) {
        this.penaltyPerDay = penaltyPerDay;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}


