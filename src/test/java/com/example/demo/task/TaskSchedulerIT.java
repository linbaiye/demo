package com.example.demo.task;

import com.example.demo.TestSetup;
import com.example.demo.model.DailyInterestCalculationTaskScheduler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskSchedulerIT extends TestSetup {

    @Autowired
    private DailyInterestCalculationTaskScheduler taskScheduler;

    @Test
    public void testDailySchedule() {
        taskScheduler.dailySchedule();
    }
}
