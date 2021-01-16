package com.example.demo.repository;

import com.example.demo.entity.InstallmentEntity;
import com.example.demo.entity.LoanEntity;
import com.example.demo.infra.LoanJpa;
import com.example.demo.model.Loan;
import com.example.demo.model.Money;
import com.example.demo.model.rate.AnnualRate;
import com.example.demo.model.rate.DailyRate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Component
@AllArgsConstructor
public class LoanRepository {

    private final LoanJpa loanJpa;

    private InstallmentEntity mapToInstallment(Loan.Installment installment,
                                               LoanEntity loanEntity) {
        InstallmentEntity entity = InstallmentEntity.builder()
                .endDate(installment.getEndDate())
                .startDate(installment.getStartDate())
                .outstandingInterest(installment.getOutstandingInterest().unbox(8))
                .outstandingPrinciple(installment.getOutstandingPrinciple().unbox(8))
                .installmentNumber(installment.getInstallmentNumber())
                .id(installment.getId())
                .build();
        entity.setLoan(loanEntity);
        return entity;
    }

    private LoanEntity mapToEntity(Loan loan) {
        LoanEntity entity = LoanEntity.builder()
                .id(loan.getId())
                .annualRate(loan.getAnnualRate().unbox())
                .badDays(loan.getBadDays())
                .loanAmount(loan.getLoanAmount().unbox())
                .state(loan.getState())
                .overdueDailyRate(loan.getOverdueDailyRate().unbox())
                .startedTime(loan.getStartedTime())
                .installmentTerm(loan.getInstallmentTerm())
                .overdueDays(loan.getOverdueDays())
                .no(loan.getNo())
                .lastDailyCalculatedTime(loan.getLastDailyCalculatedTime()).build();
        entity.setInstallmentEntities(loan.getInstallments().stream()
                .map(e -> mapToInstallment(e, entity))
                .collect(Collectors.toList()));
        return entity;
    }

    private List<Loan.Installment> mapToInstallment(List<InstallmentEntity> installmentEntityList) {
        return installmentEntityList.stream().map(e ->
                new Loan.Installment(Money.of(e.getOutstandingPrinciple()), Money.of(e.getOutstandingInterest()),
                        e.getStartDate(),
                        e.getStartDate(),
                        e.getInstallmentNumber())
        ).collect(Collectors.toList());
    }

    private Loan mapToLoan(LoanEntity loanEntity) {
        return Loan.builder()
                .annualRate(new AnnualRate(loanEntity.getAnnualRate()))
                .installmentTerm(loanEntity.getInstallmentTerm())
                .startedTime(loanEntity.getStartedTime())
                .loanAmount(Money.of(loanEntity.getLoanAmount()))
                .no(loanEntity.getNo())
                .id(loanEntity.getId())
                .overdueDailyRate(new DailyRate(loanEntity.getOverdueDailyRate()))
                .badDays(loanEntity.getBadDays())
                .overdueDays(loanEntity.getOverdueDays())
                .installments(mapToInstallment(loanEntity.getInstallmentEntities()))
                .lastDailyCalculatedTime(loanEntity.getLastDailyCalculatedTime())
                .state(loanEntity.getState())
                .build();
    }

    public void save(Loan loan) {
        loanJpa.save(mapToEntity(loan));
    }

    public Loan find(String no) {
        LoanEntity loanEntity = loanJpa.findByNo(no);
        if (loanEntity == null) {
            throw new IllegalArgumentException("Not ");
        }
        return mapToLoan(loanEntity);
    }

}
