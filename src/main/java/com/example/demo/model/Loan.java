package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
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
        interest = interest.add(principle.multiply(dailyInterestRate));
    }

    public String getTipMessage() {
        if (isOverdue()) {
            return "您已逾期，为了不影响您的征信，请立即结清";
        }
        return null;
    }

}
