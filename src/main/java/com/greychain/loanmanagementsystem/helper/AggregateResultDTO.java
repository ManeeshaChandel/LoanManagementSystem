package com.greychain.loanmanagementsystem.helper;

public class AggregateResultDTO {
    private double totalRemainingAmount;
    private double totalInterest;
    private double totalPenalty;

    public double getTotalRemainingAmount() {
        return totalRemainingAmount;
    }

    public void setTotalRemainingAmount(double totalRemainingAmount) {
        this.totalRemainingAmount = totalRemainingAmount;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public double getTotalPenalty() {
        return totalPenalty;
    }

    public void setTotalPenalty(double totalPenalty) {
        this.totalPenalty = totalPenalty;
    }
}