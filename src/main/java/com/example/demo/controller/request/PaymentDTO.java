package com.example.demo.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tao.lin
 * @date 2021/1/14
 */
@Data
public class PaymentDTO {
    private BigDecimal amount;
    private LocalDateTime paidTime;
    private String loanNo;
}
