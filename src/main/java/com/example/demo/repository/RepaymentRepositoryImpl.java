package com.example.demo.repository;

import com.example.demo.entity.RepaymentEntity;
import com.example.demo.infra.RepaymentJpa;
import com.example.demo.model.Repayment;
import com.example.demo.model.RepaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author tao.lin
 * @date 2021/1/19
 */
@Repository
@AllArgsConstructor
public class RepaymentRepositoryImpl implements RepaymentRepository  {

    private final RepaymentJpa repaymentJpa;

    private RepaymentEntity mapToEntity(Repayment repayment) {
        return RepaymentEntity.builder().amount(repayment.getAmount())
                .applicationNo(repayment.getApplicationNo())
                .paidTime(repayment.getPaidTime()).build();
    }

    @Override
    public void save(Repayment repayment) {
        repaymentJpa.save(mapToEntity(repayment));
    }
}
