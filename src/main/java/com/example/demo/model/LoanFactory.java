package com.example.demo.model;

import com.example.demo.controller.request.LoanDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Component
@AllArgsConstructor
public class LoanFactory {

    public Loan create(LoanDTO loanDTO) {
        return Loan.builder()
                .no(loanDTO.getNo())
                .loanAmount(new Money(loanDTO.getLoanAmount()))
                .installmentTerm(loanDTO.getInstallmentTerm())
                .annualRate(new AnnualRate(loanDTO.getAnnualRate()))
                .startedTime(LocalDateTime.ofEpochSecond(loanDTO.getStartedTime(),0, ZoneOffset.UTC))
                .badDays(0)
                .state("NORMAL")
                .overdueDays(loanDTO.getOverdueDays())
                .overdueDailyRate(new DailyRate(loanDTO.getOverdueDailyRate()))
                .build();
    }
}
