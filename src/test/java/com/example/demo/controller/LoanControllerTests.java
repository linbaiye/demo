package com.example.demo.controller;

import com.example.demo.TestSetup;
import com.example.demo.repository.LoanRepository;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LoanControllerTests extends TestSetup {
    
    @Autowired
    private LoanRepository loanRepository;
    

    @Test
    public void testAcceptLoan() {
        LoanDTO dto = LoanDTO.builder()
                .uid(1L)
                .loanAmount("1000")
                .applicationNo("1")
                .interestRate("0.001")
                .loanTerm(30)
                .penaltyRate("0.002")
                .loanStartedTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .build();
    }

    @Data
    @Builder
    public class LoanDTO {

        private Long uid;

        private String loanAmount;

        private String applicationNo;

        private String interestRate;

        private String penaltyRate;

        private Integer loanTerm;

        private Long loanStartedTime;
        
    }

}
