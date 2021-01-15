package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@AllArgsConstructor
@Getter
public class AccountEntry {

    private final Account.Type type;

    private final Money amount;

    private final Event event;

}
