package com.example.demo.model;

import com.example.demo.entity.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Repayment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne@JoinColumn(name = "user_loan_id")
    private LoanEntity loan;

    private BigDecimal amount;

    private LocalDateTime happenedTime;

    @CreationTimestamp
    private LocalDateTime createdTime;
}
