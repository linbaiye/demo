package com.example.demo.service;

import com.example.demo.TestSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LoanServiceIT extends TestSetup {

    @Autowired
    private LoanService loanService;

    @Test
    public void name() {
//        LoanDTO loanDTO = LoanDTO.builder()
//                .loanAmount("1000")
//                .annualRate("0.1")
//                .badDays(1)
//                .installmentTerm(1)
//                .no("5")
//                .overdueDailyRate("0.0003")
//                .overdueDays(3)
//                .startedTime(new Date().getTime() / 1000)
//                .build();
//        loanService.acceptLoan(loanDTO);
        loanService.rollDaily("5");
    }
}
