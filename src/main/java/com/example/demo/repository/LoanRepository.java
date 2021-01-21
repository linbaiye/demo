package com.example.demo.repository;

import com.example.demo.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {

    void save(Loan loan);

    void update(Loan loan);

    List<Loan> findProgressingLoans(int size);

    Optional<Loan> findByApplicationNo(String no);
}
