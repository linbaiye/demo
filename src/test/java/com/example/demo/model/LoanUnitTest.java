package com.example.demo.model;

import com.example.demo.model.rate.AnnualRate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/15
 */
public class LoanUnitTest {

    @Test
    public void generateInstallments_should_divideEqually() {
        LocalDate localDate = LocalDate.of(2020, 1, 1);
        LocalDateTime startedTime = LocalDateTime.of(localDate, LocalTime.now());
        Loan loan = Loan.builder()
                .loanAmount(new Money(1000))
                .installmentTerm(3)
                .startedTime(startedTime)
                .build();
        loan.generateInstallments();

        List<Loan.Installment> installmentList = loan.getInstallments();
        Assert.assertEquals(3, installmentList.size());
        Assert.assertEquals(0, new Money("333.33333333").compareTo(installmentList.get(0).getOutstandingPrinciple()));
        Assert.assertEquals(0, new Money("333.33333333").compareTo(installmentList.get(1).getOutstandingPrinciple()));
        Assert.assertEquals(0, new Money("333.33333334").compareTo(installmentList.get(2).getOutstandingPrinciple()));

        Assert.assertTrue(LocalDate.of(2020, 1, 1).isEqual(installmentList.get(0).getStartDate()));
        Assert.assertTrue(LocalDate.of(2020, 2, 1).isEqual(installmentList.get(0).getEndDate()));
        Assert.assertTrue(LocalDate.of(2020, 2, 2).isEqual(installmentList.get(1).getStartDate()));
        Assert.assertTrue(LocalDate.of(2020, 3, 2).isEqual(installmentList.get(1).getEndDate()));

        Assert.assertEquals(0, new Money(1000).compareTo(loan.sumOutstandingPrinciple()));
    }

    @Test
    public void testTryChangeState() {
        LocalDate localDate = LocalDate.of(2020, 1, 1);
        LocalDateTime startedTime = LocalDateTime.of(localDate, LocalTime.now());
        Loan loan = Loan.builder()
                .loanAmount(new Money(1000))
                .installmentTerm(3)
                .startedTime(startedTime)
                .build();
        loan.generateInstallments();
        loan.tryChangeState();
        Assert.assertTrue(loan.isOverdue());
        Assert.assertTrue(loan.isBad());
    }

    @Test
    public void dailyRoll_should_calculateInterestCorrectly() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime startedTime = LocalDateTime.of(localDate, LocalTime.now());
        Loan loan = Loan.builder()
                .loanAmount(new Money(1000))
                .installmentTerm(3)
                .annualRate(new AnnualRate("0.360"))
                .startedTime(startedTime.plusDays(-1))
                .build();
        loan.generateInstallments();
        loan.dailyRoll();
        Assert.assertEquals(0, new Money(1).compareTo(loan.sumOutstandingInterest()));
    }
}
