package com.example.demo.model;

/**
 * @author tao.lin
 * @date 2021/1/14
 */
public interface ContractFactory {

    Contract createContract(Loan loan);
}
