package com.example.demo.entity;

import com.example.demo.model.Loan;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Data
public class LoanEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private BigDecimal loanAmount;

    private int installmentTerm;

    private String no;

    private BigDecimal annualRate;

    private BigDecimal overdueDailyRate;

    @Enumerated(EnumType.STRING)
    private Loan.State state;

    private int overdueDays;

    private int badDays;

    private LocalDateTime startedTime;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.PERSIST)
    private List<InstallmentEntity> installmentEntities;

    private LocalDateTime lastDailyCalculatedTime;

    @Version
    private Long version;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

}
