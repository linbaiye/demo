package com.example.demo.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tao.lin
 * @date 2021/1/15
 */
public class LoanUnitTest {

    @Test
    public void generateInstallments_shouldWork() {
        LocalDateTime now = LocalDateTime.now();
        Loan loan = Loan.builder()
                .loanAmount(new Money(1000))
                .installmentTerm(3)
                .startedTime(now)
                .build();
        loan.generateInstallments();

        List<Loan.Installment> installmentList = loan.getInstallments();
        System.out.println(installmentList.get(2).getOutstandingPrinciple());
        Assert.assertEquals(0, new Money("333.3333333333").compareTo(installmentList.get(0).getOutstandingPrinciple()));
        Assert.assertEquals(0, new Money("333.3333333333").compareTo(installmentList.get(1).getOutstandingPrinciple()));
        Assert.assertEquals(0, new Money("333.3333333334").compareTo(installmentList.get(2).getOutstandingPrinciple()));
    }
}
