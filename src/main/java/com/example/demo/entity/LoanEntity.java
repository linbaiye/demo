package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/15
 */
@Entity(name = "loan")
@Table(name = "loan")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal loanAmount;

    private int installmentTerm;

    private String no;

    private BigDecimal annualRate;

    private BigDecimal overdueDailyRate;

    private String state;

    private int overdueDays;

    private int badDays;

    private LocalDateTime startedTime;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.PERSIST)
    private List<InstallmentEntity> installmentEntities;

    private LocalDateTime lastDailyCalculatedTime;

    @Version
    private Long version;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

}
