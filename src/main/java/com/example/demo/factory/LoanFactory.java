package com.example.demo.factory;

import com.example.demo.controller.request.LoanDTO;
import com.example.demo.model.Loan;
import com.example.demo.model.Money;
import com.example.demo.model.rate.AnnualRate;
import com.example.demo.model.rate.DailyRate;
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
                .state(Loan.State.NORMAL)
                .overdueDays(loanDTO.getOverdueDays())
                .overdueDailyRate(new DailyRate(loanDTO.getOverdueDailyRate()))
                .build();
    }
}
