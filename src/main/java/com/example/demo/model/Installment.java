package com.example.demo.model;

import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Getter
public class Installment {

    private final Map<Account.Type, Account> accounts;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final int nr;

    public Installment(Map<Account.Type, Account> accounts,
                       LocalDate startDate,
                       LocalDate endDate,
                       int nr) {
        this.accounts = accounts;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nr = nr;
    }

    private boolean isBetweenDate(Event event) {
        return startDate.isBefore(event.happenedTime.toLocalDate())
                && endDate.isAfter(event.happenedTime.toLocalDate());
    }

    Money getAccountBalance(Account.Type type) {
        return accounts.get(type).getBalance();
    }

    boolean canAcceptEntry(AccountEntry entry) {
        return isBetweenDate(entry.getEvent());
    }

    void acceptEntry(AccountEntry entry) {
        if (canAcceptEntry(entry)) {
            accounts.get(entry.getType()).addEntry(entry);
        }
    }
}
