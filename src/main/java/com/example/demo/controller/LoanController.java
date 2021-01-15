package com.example.demo.controller;

import com.example.demo.controller.request.LoanDTO;
import com.example.demo.controller.request.PaymentDTO;
import com.example.demo.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tao.lin
 * @date 2020/12/29
 */
@RestController
@AllArgsConstructor
@Slf4j
public class LoanController {

    private final LoanService loanService;

    @PostMapping(value = "/accept")
    public String accept(@Validated @RequestBody LoanDTO loanDTO) {
        try {
            loanService.acceptLoan(loanDTO);
            return "OK";
        } catch (Exception e) {
            log.error("exception", e);
            return "BAD";
        }
    }


    @PostMapping(value = "/repay")
    public String repay(PaymentDTO paymentDTO) {
        loanService.handleRepayment(paymentDTO);
        return "OK";
    }

}

