package com.example.demo.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public class PaymentRule implements Contract.Rule {

    @Override
    public List<AccountEntry> handleEvent(Loan loan, Event event) {
        if (!(event instanceof RepaymentEvent)) {
            throw new IllegalArgumentException("");
        }
        Money balance = ((RepaymentEvent) event).getAmount();
        List<AccountEntry> accountEntryList = new LinkedList<>();
        Money principle = loan.getAccountBalance(Account.Type.PRINCIPLE);
        if (principle.compareTo(balance) <= 0) {
            accountEntryList.add(new AccountEntry(Account.Type.PRINCIPLE, balance, event));
            balance = Money.ZERO;
        } else {
            accountEntryList.add(new AccountEntry(Account.Type.PRINCIPLE, principle, event));
            balance = balance.subtract(principle);
        }
        Money interest = loan.getAccountBalance(Account.Type.INTEREST);
        if (balance.compareTo(interest) <= 0) {

        }
        return accountEntryList;
    }
}
