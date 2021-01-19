package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Loan {

    private final Long id;

    private final Long userId;

    private final String applicationNo;

    private final BigDecimal dailyInterestRate;

    private final BigDecimal overdueDailyInterestRate;

    private final BigDecimal loanAmount;

    private BigDecimal principle;

    private BigDecimal interest;

    private final LocalDateTime startedDateTime;

    private LocalDateTime dailyInterestCalculatedTime;

    /*
     * 贷款天数，从开始时间+loanTerm+1算起，如果没有还清，则视为逾期
     */
    private final Integer loanTerm;

    private State state;

    public enum State {
        NORMAL,
        OVERDUE,
        PAID,
    }

    /**
     * 计算出用户的待还金额
     * @return 待还总额
     */
    public BigDecimal calculateOutstandingBalance() {
        return principle.add(interest);
    }

    private boolean isPaid() {
        return interest.compareTo(BigDecimal.ZERO) == 0 &&
                principle.compareTo(BigDecimal.ZERO) == 0;
    }

    private boolean isOverdue() {
        LocalDate now = LocalDate.now();
        LocalDate overdueDate = startedDateTime.toLocalDate().plusDays(loanTerm + 1);
        return (now.isAfter(overdueDate) || now.isEqual(overdueDate))
                && !isPaid();
    }

    public void calculateDailyInterest() {
        interest = interest.add(principle.multiply(dailyInterestRate)).setScale(2, RoundingMode.HALF_UP);
        dailyInterestCalculatedTime = LocalDateTime.now();
    }

    public String getTipMessage() {
        if (isOverdue()) {
        }
        return null;
    }

    public OverRepayment repay(Repayment repayment) {
        BigDecimal paymentAmount = repayment.getAmount();
        if (paymentAmount.compareTo(interest) >= 0) {
            paymentAmount = paymentAmount.subtract(interest);
            interest = BigDecimal.ZERO;
        } else {
            interest = interest.subtract(paymentAmount);
            paymentAmount = BigDecimal.ZERO;
        }
        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        if (paymentAmount.compareTo(principle) >= 0) {
            paymentAmount = paymentAmount.subtract(principle);
            principle = BigDecimal.ZERO;
        } else {
            principle = principle.subtract(paymentAmount);
            paymentAmount = BigDecimal.ZERO;
        }
        return paymentAmount.compareTo(BigDecimal.ZERO) > 0 ?
                new OverRepayment(this.id, paymentAmount) : null;
    }

}
