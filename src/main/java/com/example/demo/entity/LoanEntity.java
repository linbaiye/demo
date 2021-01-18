package com.example.demo.entity;

import com.example.demo.model.Loan;
import com.example.demo.model.Repayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity(name = "loan")
@Table(name = "user_loan")
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String applicationNo;

    private BigDecimal dailyInterestRate;

    private BigDecimal overdueDailyInterestRate;

    private BigDecimal loanAmount;

    private BigDecimal principle;

    private BigDecimal interest;

    private LocalDateTime startedDateTime;

    private Integer loanTerm;

    @Enumerated(value = EnumType.STRING)
    private Loan.State state;

    private LocalDateTime dailyInterestCalculatedTime;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @Version
    private Integer version;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @OneToMany(mappedBy = "loan")
    private List<Repayment> repayments;

}
