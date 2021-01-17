package com.example.demo.repository;

import com.example.demo.entity.LoanEntity;
import com.example.demo.infra.LoanJpa;
import com.example.demo.model.Loan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LoanRepository {

    private final LoanJpa loanJpa;

    private LoanEntity mapToEntity(Loan loan) {
        return LoanEntity.builder()
                .applicationNo(loan.getApplicationNo())
                .dailyInterestRate(loan.getDailyInterestRate())
                .loanTerm(loan.getLoanTerm())
                .loanAmount(loan.getLoanAmount())
                .interest(loan.getInterest())
                .principle(loan.getPrinciple())
                .dailyInterestRate(loan.getDailyInterestRate())
                .overdueDailyInterestRate(loan.getOverdueDailyInterestRate())
                .startedDateTime(loan.getStartedDateTime())
                .userId(loan.getUserId())
                .state(loan.getState())
                .build();
    }

    public LoanEntity updateToEntity(Loan loan, LoanEntity entity) {
        entity.setInterest(loan.getInterest());
        entity.setState(loan.getState());
        entity.setPrinciple(loan.getPrinciple());
        return entity;
    }

    public void save(Loan loan) {
        loanJpa.save(mapToEntity(loan));
    }

}
