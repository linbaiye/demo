package com.example.demo.model;

import com.example.demo.model.rate.Rate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public class Money {

    private final BigDecimal amount;
    private static final int SCALE = 8;

    public final static Money ZERO = new Money(0);

    public Money(BigDecimal amount) {
        this.amount = amount.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public Money(String amount){
        this(new BigDecimal(amount));
    }

    public Money(int num) {
        this(new BigDecimal(num));
    }

    public Money add(Money that) {
        return new Money(this.amount.add(that.amount));
    }

    public Money subtract(Money that) {
        return new Money(this.amount.subtract(that.amount));
    }

    public Money divide(int nr) {
        if (nr <= 0) {
            throw new IllegalArgumentException("");
        }
        return new Money(amount.divide(new BigDecimal(nr), SCALE, RoundingMode.HALF_UP));
    }

    public Money divide(BigDecimal divider) {
        if (divider.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("");
        }
        return new Money(amount.divide(divider, SCALE, RoundingMode.HALF_UP));
    }

    public Money multiply(int nr) {
        return new Money(amount.multiply(new BigDecimal(nr)));
    }

    public int compareTo(Money that) {
        return amount.compareTo(that.amount);
    }

    public Money multiply(Rate rate) {
        return new Money(this.amount.multiply(rate.unbox()));
    }

    public BigDecimal unbox() {
        return unbox(SCALE);
    }


    public BigDecimal unbox(int scale) {
        return amount.setScale(scale, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }
}
