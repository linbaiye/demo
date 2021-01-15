package com.example.demo.controller;

import com.example.demo.controller.request.PaymentDTO;
import com.example.demo.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tao.lin
 * @date 2020/12/29
 */
@RestController
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping(value = "/repay")
    public String repay(PaymentDTO paymentDTO) {
        loanService.handleRepayment(paymentDTO);
        return "OK";
    }

}

