package com.example.demo.factory;

import com.example.demo.controller.request.RepaymentDTO;
import com.example.demo.model.Repayment;
import org.springframework.stereotype.Component;

/**
 * @author tao.lin
 * @date 2021/1/19
 */
@Component
public class RepaymentFactory {

    public Repayment create(RepaymentDTO repaymentDTO) {
        return new Repayment(null, repaymentDTO.getLoanNo(), repaymentDTO.getAmount(), repaymentDTO.getPaidTime());
    }
}
