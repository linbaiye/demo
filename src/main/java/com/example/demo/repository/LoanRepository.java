package com.example.demo.repository;

import com.example.demo.model.Loan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Component
@AllArgsConstructor
public class LoanRepository {

    public void save(Loan loan) { }

    public Loan find(String no) {
        return null;
    }

}
