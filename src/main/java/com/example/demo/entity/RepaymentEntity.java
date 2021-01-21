package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tao.lin
 * @date 2021/1/19
 */
@Data
@Builder
@Entity(name = "repayment")
@Table(name = "repayment")
@NoArgsConstructor
public class RepaymentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicationNo;

    private BigDecimal amount;

    private LocalDateTime paidTime;

}
