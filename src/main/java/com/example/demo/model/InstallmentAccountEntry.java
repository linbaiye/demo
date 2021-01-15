package com.example.demo.model;

import lombok.Getter;

/**
 * @author tao.lin
 * @date 2021/1/14
 */
@Getter
public class InstallmentAccountEntry extends AccountEntry {

    private final int installmentNumber;

    public InstallmentAccountEntry(int installmentNumber,
                                   Account.Type type,
                                   Money amount,
                                   Event event) {
        super(type, amount, event);
        this.installmentNumber = installmentNumber;
    }
}
