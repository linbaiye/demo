package com.example.demo.model;

import java.time.LocalDateTime;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public class RepaymentEvent extends Event {
    private final Money amount;

    public RepaymentEvent(Money money,
                          LocalDateTime paidTime,
                          LocalDateTime noticedTime) {
        super(Type.REPAYMENT, paidTime, noticedTime);
        this.amount = money;
    }

    public Money getAmount() {
        return amount;
    }
}
