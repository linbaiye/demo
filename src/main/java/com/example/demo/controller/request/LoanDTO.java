package com.example.demo.controller.request;

import lombok.Data;

/**
 * @author tao.lin
 * @date 2021/1/15
 */
@Data
public class LoanDTO {

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
