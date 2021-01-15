package com.example.demo.service;

import com.example.demo.controller.request.PaymentDTO;
import com.example.demo.model.*;
import com.example.demo.repository.LoanRepository;
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

    private final LoanFactory loanFactory;

    private final LoanRepository loanRepository;

    @Transactional(rollbackFor = Exception.class)
    public void accpetLoan() {
        Loan loan = loanFactory.create();
        loan.generateInstallments();
        loanRepository.save(loan);
    }

    @Transactional(rollbackFor = Exception.class)
    public void handleRepayment(PaymentDTO paymentDTO) {
        Loan loan = loanRepository.find(paymentDTO.getLoanNo());
        loan.repay(paymentDTO.getAmount(), paymentDTO.getPaidTime());
        loanRepository.save(loan);
    }
}
