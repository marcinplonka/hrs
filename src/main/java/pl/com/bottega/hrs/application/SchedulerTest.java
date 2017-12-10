package pl.com.bottega.hrs.application;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTest {

    @Scheduled(cron = "0 43 15 * * ?")
    public void test() {
        Logger.getLogger(SchedulerTest.class).info("Executing sheduler test");
    }
}
