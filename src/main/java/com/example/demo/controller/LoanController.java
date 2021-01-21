package com.example.demo.controller;

import com.example.demo.controller.mapper.LoanResponseMapper;
import com.example.demo.controller.request.LoanDTO;
import com.example.demo.controller.request.RepaymentDTO;
import com.example.demo.controller.response.QueryLoanRespDTO;
import com.example.demo.factory.LoanFactory;
import com.example.demo.repository.LoanRepository;
import com.example.demo.model.OverRepayment;
import com.example.demo.service.LoanService;
import com.example.demo.service.OverRepaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author tao.lin
 * @date 2020/12/29
 */
@RestController
@AllArgsConstructor
@Slf4j
public class LoanController {

    private final LoanService loanService;

    private final LoanResponseMapper loanResponseMapper;

    private final LoanRepository loanRepository;

    private final LoanFactory loanFactory;

    private final OverRepaymentService overRepaymentService;

    @PostMapping(value = "/record-loan")
    public String recordLoan(@Validated @RequestBody LoanDTO loanDTO) {
        try {
            loanRepository.save(loanFactory.create(loanDTO));
            return "OK";
        } catch (Exception e) {
            log.error("exception", e);
            return "BAD";
        }
    }

    @PostMapping(value = "/view")
    public QueryLoanRespDTO view(@RequestParam(name = "applicationNo") String applicationNo) {
        return loanResponseMapper.map(
                loanRepository.findByApplicationNo(applicationNo).orElseThrow(IllegalArgumentException::new)
        );
    }

    @PostMapping(value = "/repay")
    public String repay(@RequestBody RepaymentDTO repaymentDTO) {
        OverRepayment overRepayment = loanService.repay(repaymentDTO);
        if (overRepayment != null) {
            overRepaymentService.processOverpayment(overRepayment);
        }
        return "OK";
    }

}

