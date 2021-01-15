package com.example.demo.model;

import java.math.BigDecimal;

/**
 * @author tao.lin
 * @date 2021/1/14
 */
public class AnnualRate extends Rate {

    public AnnualRate(BigDecimal v) {
        super(v);
    }

    public AnnualRate(String v) {
        super(new BigDecimal(v));
    }

    @Override
    public Rate toDaily() {
        return new DailyRate(v.divide(new BigDecimal(360), SCALE, ROUND_MODE));
    }

    @Override
    public Rate toMonthly() {
        return toDaily().toMonthly();
    }
}
