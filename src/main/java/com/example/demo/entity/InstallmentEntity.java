package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author tao.lin
 * @date 2021/1/15
 */
@Entity(name = "installment")
@Table(name = "installment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private LoanEntity loan;

    private BigDecimal outstandingPrinciple;

    private BigDecimal outstandingInterest;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer installmentNumber;

}
