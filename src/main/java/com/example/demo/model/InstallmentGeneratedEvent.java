package com.example.demo.model;

import java.time.LocalDateTime;

/**
 * @author tao.lin
 * @date 2021/1/14
 */
public class InstallmentGeneratedEvent extends Event {

    public InstallmentGeneratedEvent() {
        super(Type.LOAN_ACCEPTED,
              LocalDateTime.now(),
              LocalDateTime.now());
    }
}
