package com.greychain.loanmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String customerId;

    @OneToMany(mappedBy = "customer")
    private List<Loan> loans;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
