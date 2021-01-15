package com.example.demo.model;

import java.util.Collections;
import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public class DailyInterestRule implements Contract.Rule {

    @Override
    public List<AccountEntry> handleEvent(Loan loan, Event event) {
        Money amount = loan.getAccountBalance(Account.Type.PRINCIPLE);
        return Collections.singletonList(new AccountEntry(Account.Type.INTEREST, amount.multiply(loan.getAnnualRate().toDaily()), event));
    }
}
