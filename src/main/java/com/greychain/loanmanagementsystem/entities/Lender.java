package com.greychain.loanmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lender")
public class Lender {
    @Id
    @Column(name = "lender_id")
    private int lenderId;

    @OneToMany(mappedBy = "lender")
    private List<Loan> loans;

    public int getLenderId() {
        return lenderId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
