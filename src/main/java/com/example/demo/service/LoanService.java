package com.example.demo.service;

import com.example.demo.controller.request.LoanDTO;
import com.example.demo.factory.LoanFactory;
import com.example.demo.model.Loan;
import com.example.demo.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Service
@AllArgsConstructor
public class LoanService {

    private final LoanFactory loanFactory;

    private final LoanRepository loanRepository;

    public void accept(LoanDTO dto) {
        loanRepository.save(loanFactory.create(dto));
    }

}
