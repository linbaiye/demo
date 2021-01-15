package com.example.demo.model;

import java.time.LocalDateTime;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public abstract class Event {
    Type type;

    LocalDateTime happenedTime;

    LocalDateTime noticedTime;

    public Event(Type type) {
        this.type = type;
    }

    public Event(Type type, LocalDateTime happenedDate, LocalDateTime noticedWhen) {
        this.type = type;
        this.happenedTime = happenedDate;
        this.noticedTime = noticedWhen;
    }

    public enum Type {
        DAY_PAST,
        REPAYMENT,
        LOAN_ACCEPTED,
    }
}
