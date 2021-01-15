package com.example.demo.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Component
@AllArgsConstructor
public class LoanFactory {

    public Loan create() {
        return Loan.builder()
                .no("1234")
                .loanAmount(new Money(1000))
                .installmentTerm(3)
                .annualRate(new AnnualRate("0.25"))
                .events(new LinkedList<>())
                .build();
    }
}
