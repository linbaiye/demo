package com.example.demo.infra;

import com.example.demo.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoanJpa extends JpaRepository<LoanEntity, Long> {
}
