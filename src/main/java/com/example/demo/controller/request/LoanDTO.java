package com.example.demo.controller.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author tao.lin
 * @date 2021/1/15
 */
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
