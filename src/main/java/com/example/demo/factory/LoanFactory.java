package com.example.demo.factory;

import com.example.demo.controller.request.LoanDTO;
import com.example.demo.model.Loan;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class LoanFactory {

    public Loan create(LoanDTO dto) {
        return Loan.builder()
                .interest(BigDecimal.ZERO)
                .state(Loan.State.NORMAL)
                .dailyInterestRate(new BigDecimal(dto.getInterestRate()))
                .loanTerm(dto.getLoanTerm())
                .principle(new BigDecimal(dto.getLoanAmount()))
                .overdueDailyInterestRate(new BigDecimal(dto.getPenaltyRate()))
                .applicationNo(dto.getApplicationNo())
                .userId(dto.getUid())
                .startedDateTime(LocalDateTime.ofEpochSecond(dto.getLoanStartedTime(), 0, ZoneOffset.UTC))
                .loanAmount(new BigDecimal(dto.getLoanAmount()))
                .build();
    }
}
