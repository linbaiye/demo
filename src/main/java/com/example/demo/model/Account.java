package com.example.demo.model;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Getter
public abstract class Account {

    private final Type type;

    private Money balance;

    private final List<AccountEntry> accountEntryList;

    public Account(Type type, Money balance, List<AccountEntry> accountEntryList) {
        this.type = type;
        this.accountEntryList = accountEntryList == null ? new LinkedList<>() : accountEntryList;
        this.balance = balance == null ? new Money(0) : balance;
    }

    public void addEntry(AccountEntry entry) {
        if (entry.getType() == this.type) {
            accountEntryList.add(entry);
            this.balance = balance.add(entry.getAmount());
        }
    }

    public enum Type {
        INTEREST,
        PRINCIPLE,
    }
}
