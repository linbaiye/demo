package com.example.demo.repository;

import com.example.demo.entity.LoanEntity;
import com.example.demo.infra.LoanJpa;
import com.example.demo.model.Loan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class LoanRepositoryImpl implements LoanRepository {

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

    private LoanEntity updateToEntity(Loan loan, LoanEntity entity) {
        entity.setInterest(loan.getInterest());
        entity.setState(loan.getState());
        entity.setPrinciple(loan.getPrinciple());
        entity.setDailyInterestCalculatedTime(loan.getDailyInterestCalculatedTime());
        return entity;
    }

    private Loan mapToLoan(LoanEntity loanEntity) {
        if (loanEntity == null) {
            return null;
        }
        return Loan.builder()
                .loanAmount(loanEntity.getLoanAmount())
                .startedDateTime(loanEntity.getStartedDateTime())
                .applicationNo(loanEntity.getApplicationNo())
                .userId(loanEntity.getUserId())
                .overdueDailyInterestRate(loanEntity.getOverdueDailyInterestRate())
                .principle(loanEntity.getPrinciple())
                .interest(loanEntity.getInterest())
                .id(loanEntity.getId())
                .state(loanEntity.getState())
                .dailyInterestRate(loanEntity.getDailyInterestRate())
                .loanTerm(loanEntity.getLoanTerm())
                .dailyInterestCalculatedTime(loanEntity.getDailyInterestCalculatedTime())
                .build();
    }

    @Override
    public void save(Loan loan) {
        loanJpa.save(mapToEntity(loan));
    }

    @Override
    public void update(Loan loan) {
        LoanEntity entity = loanJpa.findById(loan.getId()).orElseThrow(IllegalArgumentException::new);
        loanJpa.save(updateToEntity(loan, entity));
    }

    @Override
    public List<Loan> findProgressingLoans(int size) {
        return loanJpa.findAll().stream().map(this::mapToLoan).collect(Collectors.toList());
    }

    @Override
    public Optional<Loan> findByApplicationNo(String no) {
        return Optional.ofNullable(mapToLoan(loanJpa.findByApplicationNo(no)));
    }

}
