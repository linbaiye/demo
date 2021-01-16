package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author tao.lin
 * @date 2021/1/15
 */
@Entity(name = "installment")
@Table(name = "installment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstallmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;

    private BigDecimal outstandingPrinciple;

    private BigDecimal outstandingInterest;

    private Integer installmentNumber;

    private LocalDate startDate;

    private LocalDate endDate;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

}
