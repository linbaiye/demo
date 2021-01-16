package com.example.demo.controller;

import com.example.demo.TestSetup;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


/**
 * @author tao.lin
 * @date 2021/1/15
 */
public class LoanControllerIT extends TestSetup  {

    @Builder
    @Data
    private static class LoanDTO {

        private Long id;

        private String loanAmount;

        private Integer installmentTerm;

        private String no;

        private String annualRate;

        private String overdueDailyRate;

        private Integer overdueDays;

        private Integer badDays;

        private Long startedTime;
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    private void postAccept(LoanDTO loanDTO) throws Exception {
        mockMvc.perform(post("/accept")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(loanDTO)))
                .andDo(print());

    }

    @Test
    public void name() throws Exception {
        postAccept(LoanDTO.builder()
                .loanAmount("1000")
                .annualRate("0.1")
                .badDays(1)
                .installmentTerm(3)
                .no("4")
                .overdueDailyRate("0.0003")
                .overdueDays(3)
                .startedTime(new Date().getTime() / 1000)
                .build()
        );
    }
}
