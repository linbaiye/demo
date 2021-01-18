package com.example.demo.model;

import com.example.demo.entity.LoanEntity;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {

    void save(Loan loan);

    void update(Loan loan);

    List<Loan> findProgressingLoans(int size);

    Optional<Loan> findByNo(String no);
}
