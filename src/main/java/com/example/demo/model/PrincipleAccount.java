package com.example.demo.model;

import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public class PrincipleAccount extends Account {

    private final Installment installment;

    public PrincipleAccount(Money balance, List<AccountEntry> accountEntryList) {
        super(Type.PRINCIPLE, balance, accountEntryList);
        this.installment = null;
    }
}
