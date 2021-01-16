package com.example.demo.model.rate;

import java.math.BigDecimal;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public abstract class Rate {

    final BigDecimal v;

    static final int SCALE = 10;

    static final int ROUND_MODE = BigDecimal.ROUND_DOWN;

    public Rate(BigDecimal v) {
        this.v = v.setScale(SCALE, ROUND_MODE);
    }

    public abstract Rate toDaily();

    public abstract Rate toMonthly();

    public BigDecimal unbox() {
        return v;
    }

}
