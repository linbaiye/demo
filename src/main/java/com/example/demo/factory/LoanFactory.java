package com.example.demo.factory;

import com.example.demo.controller.request.LoanDTO;
import com.example.demo.model.Loan;
import com.example.demo.model.LoanValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class LoanFactory {

    private static final LoanValidator LOAN_VALIDATOR = new LoanValidator();

    public Loan create(LoanDTO dto) {
        Loan loan = Loan.builder()
                .interest(BigDecimal.ZERO)
                .state(Loan.State.NORMAL)
                .dailyInterestRate(new BigDecimal(dto.getInterestRate()))
                .loanTerm(dto.getLoanTerm())
                .principle(new BigDecimal(dto.getLoanAmount()).setScale(4, RoundingMode.HALF_UP))
                .overdueDailyInterestRate(new BigDecimal(dto.getPenaltyRate()))
                .applicationNo(dto.getApplicationNo())
                .userId(dto.getUid())
                .startedDateTime(LocalDateTime.ofEpochSecond(dto.getLoanStartedTime(), 0, ZoneOffset.UTC))
                .loanAmount(new BigDecimal(dto.getLoanAmount()))
                .build();
        LOAN_VALIDATOR.checkInvariant(loan);
        return loan;
    }
}
