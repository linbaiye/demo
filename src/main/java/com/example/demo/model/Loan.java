package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Getter
@Builder
public class Loan {

    private final Long id;

    private final Money loanAmount;

    private final int installmentTerm;

    private final String no;

    private final Rate annualRate;

    private final Rate overdueDailyRate;

    private String state;

    private final int overdueDays;

    private final int badDays;

    private final LocalDateTime startedTime;

    private List<Installment> installments;

    private LocalDateTime lastDailyCalculatedTime;

    public Loan(Long id,
                Money loanAmount,
                int installmentTerm,
                String no,
                Rate annualRate,
                Rate overdueDailyRate,
                String state,
                int overdueDays,
                int badDays,
                LocalDateTime startedTime,
                List<Installment> installments, LocalDateTime lastDailyCalculatedTime) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.installmentTerm = installmentTerm;
        this.no = no;
        this.annualRate = annualRate;
        this.overdueDailyRate = overdueDailyRate;
        this.state = state;
        this.overdueDays = overdueDays;
        this.badDays = badDays;
        this.startedTime = startedTime;
        this.installments = installments;
        this.lastDailyCalculatedTime = lastDailyCalculatedTime;
    }

    private boolean isClosed() {
        return "CLOSED".equals(state) || "BAD".equals(state);
    }

    public void generateInstallments() {
        LocalDate startDate = startedTime.toLocalDate();
        Money avg = loanAmount.divide(installmentTerm);
        Money sum = Money.ZERO;
        installments = new LinkedList<>();
        for (int i = 0; i < installmentTerm; i++) {
            if (i == installmentTerm - 1) {
                installments.add(new Installment(loanAmount.subtract(sum), Money.ZERO, startDate, startDate.plusMonths(1), i + 1));
            } else {
                Installment installment = new Installment(avg, Money.ZERO, startDate, startDate.plusMonths(1), i + 1);
                installments.add(installment);
                sum = sum.add(avg);
            }
        }
    }

    public List<Installment> listInstallments() {
        return new ArrayList<>(installments);
    }

    private void handleEvent(Event e) {

    }

    private void changeStateIfSo(LocalDateTime now) {
        for (Installment installment : installments) {
            if (installment.isBad(now.toLocalDate(), badDays)) {
                state = "BAD";
                return;
            } else if (installment.isOverdue(now.toLocalDate(), overdueDays)) {
                state = "OVERDUE";
                return;
            }
        }
    }

    public void dailyRoll() {
        LocalDateTime now = LocalDateTime.now();
        if (now.toLocalDate().isEqual(lastDailyCalculatedTime.toLocalDate())) {
            throw new IllegalStateException("Daily roll done.");
        }
        changeStateIfSo(now);
        if (isClosed()) {
            return;
        }
        Money outstandingPrinciple = sumOutstandingPrinciple();
        Money interest = outstandingPrinciple.multiply(isOverdue() ? overdueDailyRate : annualRate.toDaily());
        for (Installment installment : installments) {
            if (!installment.isPaid()) {
                installment.addToOutstandingInterest(interest);
                break;
            }
        }
    }

    private boolean isOverdue() {
        return "OVERDUE".equals(state) ||
                "BAD".equals(state);
    }

    private Money sumOutstandingPrinciple() {
        Money sum = new Money(0);
        for (Installment installment : installments) {
            sum.add(installment.getOutstandingPrinciple());
        }
        return sum;
    }

    public void repay(BigDecimal amount, LocalDateTime paidTime) {
        if (isClosed()) {
            throw new IllegalStateException("Loan closed.");
        }
        handleEvent(new RepaymentEvent(new Money(amount), paidTime, LocalDateTime.now()));
    }

    public Money sumOutstandingPrinciple(Account.Type type) {
        Money sum = new Money(0);
        for (Installment installment : installments) {
//            sum = sum.add(installment.getAccountBalance(type));
        }
        return sum;
    }

    /**
     * @author tao.lin
     * @date 2021/1/13
     */
    @Getter
    public static class Installment {

        private Money outstandingPrinciple;

        private Money outstandingInterest;

        private final LocalDate startDate;

        private final LocalDate endDate;

        private final int installmentNumber;

        public Installment(Money outstandingPrinciple,
                           Money outstandingInterest,
                           LocalDate startDate,
                           LocalDate endDate,
                           int installmentNumber) {
            // invariant check.
            this.outstandingPrinciple = outstandingPrinciple;
            this.outstandingInterest = outstandingInterest;
            this.startDate = startDate;
            this.endDate = endDate;
            this.installmentNumber = installmentNumber;
        }

        void repay(Money paidPrinciple, Money paidInterest) {
            // invariant check, 比如还款不能为负数，还清了不能再还，etc..
            this.outstandingPrinciple = this.outstandingPrinciple.subtract(paidPrinciple);
            this.outstandingInterest = this.outstandingInterest.subtract(paidInterest);
        }

        void addToOutstandingInterest(Money interest) {
            if (isPaid()) {
                throw new IllegalStateException();
            }
            this.outstandingInterest = this.outstandingInterest.add(interest);
        }

        boolean isPaid() {
            return outstandingPrinciple.compareTo(Money.ZERO) == 0 &&
                    outstandingInterest.compareTo(Money.ZERO) == 0;
        }

        boolean isOverdue(LocalDate date, int days) {
            return endDate.plusDays(days).isAfter(date) && !isPaid();
        }

        boolean isBad(LocalDate date, int days) {
            return isOverdue(date, days);
        }
    }
}
