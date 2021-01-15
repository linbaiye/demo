package com.example.demo.repository;

import com.example.demo.entity.InstallmentEntity;
import com.example.demo.entity.LoanEntity;
import com.example.demo.infra.LoanJpa;
import com.example.demo.model.Loan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Component
@AllArgsConstructor
public class LoanRepository {

    private final LoanJpa loanJpa;

    private InstallmentEntity mapToInstallment(Loan.Installment installment) {
        return InstallmentEntity.builder()
                .endDate(installment.getEndDate())
                .startDate(installment.getStartDate())
                .outstandingInterest(installment.getOutstandingInterest().unbox(8))
                .outstandingPrinciple(installment.getOutstandingPrinciple().unbox(8))
                .installmentNumber(installment.getInstallmentNumber())
                .build();
    }

    private LoanEntity mapToEntity(Loan loan) {
        return LoanEntity.builder()
                .annualRate(loan.getAnnualRate().unbox())
                .badDays(loan.getBadDays())
                .installmentEntities(loan.getInstallments().stream().map(this::mapToInstallment).collect(Collectors.toList()))
                .loanAmount(loan.getLoanAmount().unbox())
                .state(loan.getState())
                .overdueDailyRate(loan.getOverdueDailyRate().unbox())
                .startedTime(loan.getStartedTime())
                .installmentTerm(loan.getInstallmentTerm())
                .overdueDays(loan.getOverdueDays())
                .no(loan.getNo())
                .lastDailyCalculatedTime(loan.getLastDailyCalculatedTime()).build();
    }

    public void save(Loan loan) {
        loanJpa.save(mapToEntity(loan));
    }

    public Loan find(String no) {
        return null;
    }

}
