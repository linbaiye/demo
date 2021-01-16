package com.example.demo.model;

import com.example.demo.model.rate.Rate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 按日等额本息
 * 每月本金计算公式为： a=贷款总金额，i=月利率， N=还贷总月数， n=第n个月每月应还本金 = a*i(1+i)^(n-1)/[(1+i)^N-1]
 * 每月应还利息=剩余正常未还本金*日利率*资金占用天数
 * 整笔贷款算头不算尾
 * 非最后一期还款日利息在下一期收取,最后一期还款日不收取利息
 */
public class DailyEqualPrincipleCalculator implements Contract.InstallmentGenerateRule {

    @Override
    public List<InstallmentAccountEntry> generate(Loan loan, Event event) {
        Rate monthlyRate = loan.getAnnualRate().toMonthly();
        BigDecimal ratePlusOne = monthlyRate.unbox().add(new BigDecimal(1));
        BigDecimal amount = loan.getLoanAmount().unbox();
        BigDecimal divider = ratePlusOne.pow(loan.getInstallmentTerm()).subtract(new BigDecimal(1));
        List<InstallmentAccountEntry> accountEntries = new ArrayList<>(loan.getInstallmentTerm());
        for (int i = 0; i < loan.getInstallmentTerm(); i++) {
            Money thisInstallmentPrinciple = new Money(amount.multiply(monthlyRate.unbox()).multiply(ratePlusOne).pow(i)).divide(divider);
            accountEntries.add(new InstallmentAccountEntry(i + 1, Account.Type.PRINCIPLE, thisInstallmentPrinciple, event));
        }
        return accountEntries;
    }
}
