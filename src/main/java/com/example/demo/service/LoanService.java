package com.example.demo.service;

import com.example.demo.controller.request.RepaymentDTO;
import com.example.demo.factory.RepaymentFactory;
import com.example.demo.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Service
@AllArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    private final RepaymentRepository repaymentRepository;

    private final RepaymentFactory repaymentFactory;

    @Transactional(rollbackFor = Exception.class)
    public OverRepayment repay(RepaymentDTO repaymentDTO) {
        Repayment repayment = repaymentFactory.create(repaymentDTO);
        Loan loan = loanRepository.findByApplicationNo(repaymentDTO.getLoanNo()).orElseThrow(IllegalArgumentException::new);
        OverRepayment repaymentRefund = loan.repay(repayment);
        loanRepository.update(loan);
        repaymentRepository.save(repayment);
        return repaymentRefund;
    }

}
