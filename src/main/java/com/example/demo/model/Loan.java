package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Getter
@Builder
public class Loan {

    private Long id;

    private final Money loanAmount;

    private final int installmentTerm;

    private final String installmentPeriod;

    private final String no;

    private final Rate annualRate;

    private final LocalDateTime startedTime;

    private Map<Integer, Installment> installments;

    private final List<Event> events;

    private final Contract contract;

    private boolean isClosed() {
        return false;
    }

    public void generateInstallments() {
        if (installments != null && !installments.isEmpty()) {
            throw new IllegalStateException("");
        }
        installments = new HashMap<>(installmentTerm);
        LocalDate startDate = startedTime.toLocalDate();
        for (int i = 0; i < installmentTerm; i++) {
            Installment installment = new Installment(new HashMap<>(), startDate, startDate.plusMonths(1), i + 1);
            installments.put(i + 1, installment);
            startDate = startDate.plusMonths(1);
        }
        List<InstallmentAccountEntry> accountEntries = contract.createInitializationInstallmentEntries(this);
        for (InstallmentAccountEntry entry : accountEntries) {
            installments.get(entry.getInstallmentNumber()).acceptEntry(entry);
        }
    }

    private void handleEvent(Event e) {
        List<AccountEntry> entryList = contract.handleEvent(this, e);
        for (AccountEntry entry : entryList) {
            for (Installment installment : installments.values()) {
                if (installment.canAcceptEntry(entry)) {
                    installment.acceptEntry(entry);
                }
            }
        }
        events.add(e);
    }

    public void repay(BigDecimal amount, LocalDateTime paidTime) {
        if (isClosed()) {
            throw new IllegalStateException("Loan closed.");
        }
        handleEvent(new RepaymentEvent(new Money(amount), paidTime, LocalDateTime.now()));
    }

    public Money getAccountBalance(Account.Type type) {
        Money sum = new Money(0);
        for (Installment installment : installments.values()) {
            sum = sum.add(installment.getAccountBalance(type));
        }
        return sum;
    }
}
