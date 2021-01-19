package com.example.demo.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DailyTaskScheduler {

    private final LoanRepository loanRepository;

    public void dailySchedule() {
        List<Loan> loanList = loanRepository.findProgressingLoans(10);
        for (Loan loan : loanList) {
            loan.calculateDailyInterest();
            loanRepository.update(loan);
        }
    }
}
