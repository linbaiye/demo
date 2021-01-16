package com.example.demo.model.rate;

import java.math.BigDecimal;

/**
 * @author tao.lin
 * @date 2021/1/14
 */
public class MonthlyRate extends Rate {

    public MonthlyRate(BigDecimal v) {
        super(v);
    }

    @Override
    public Rate toDaily() {
        return new DailyRate(v.divide(new BigDecimal(30), SCALE, ROUND_MODE));
    }

    @Override
    public Rate toMonthly() {
        return this;
    }
}
