package com.example.demo.model;

import java.time.LocalDateTime;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
public class DayPastEvent extends Event {

    private final LocalDateTime dateTime;

    public DayPastEvent(LocalDateTime dateTime) {
        super(Type.DAY_PAST);
        this.dateTime = dateTime;
    }
}
