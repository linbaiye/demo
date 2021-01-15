package com.example.demo.model;

import java.math.BigDecimal;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public class DailyRate extends Rate {

    public DailyRate(BigDecimal v) {
        super(v);
    }

    @Override
    public Rate toDaily() {
        return this;
    }

    @Override
    public Rate toMonthly() {
        return new MonthlyRate(v.multiply(new BigDecimal(30)));
    }
}
