package com.example.demo.model;

import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public class InterestAccount extends Account {

    public InterestAccount(Money balance, List<AccountEntry> accountEntryList) {
        super(Type.INTEREST, balance, accountEntryList);
    }
}
