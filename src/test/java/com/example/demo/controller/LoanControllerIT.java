package com.example.demo.controller;

import com.example.demo.TestSetup;
import com.example.demo.model.Loan;
import com.example.demo.repository.LoanRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class LoanControllerIT extends TestSetup {
    
    @Autowired
    private LoanRepositoryImpl loanRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private void postRecordLoan(LoanDTO loanDTO) throws Exception {
        mockMvc.perform(post("/record-loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(loanDTO)))
               .andDo(print());
    }

    @Test
    public void testAcceptLoan() throws Throwable {
        LoanDTO dto = LoanDTO.builder()
                .uid(1L)
                .loanAmount("1000")
                .applicationNo("1")
                .interestRate("0.001")
                .loanTerm(30)
                .penaltyRate("0.002")
                .loanStartedTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .build();
        postRecordLoan(dto);
        Loan savedEntity = loanRepository.findByApplicationNo("1").orElseThrow(IllegalStateException::new);
        Assert.assertEquals(30, savedEntity.getLoanTerm().intValue());
        // 更多的Assert.
    }

    @Data
    @Builder
    public static class LoanDTO {

        private Long uid;

        private String loanAmount;

        private String applicationNo;

        private String interestRate;

        private String penaltyRate;

        private Integer loanTerm;

        private Long loanStartedTime;
    }

}
