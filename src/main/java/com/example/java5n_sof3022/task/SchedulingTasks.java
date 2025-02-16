package com.example.java5n_sof3022.task;

import com.example.java5n_sof3022.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor // Lombok will generate a constructor if the class has final fields
@Slf4j // for logging from Lombok
public class SchedulingTasks {

    //private static final Logger log = LoggerFactory.getLogger(SchedulingTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final ProductService productService;



    /* 1
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        log.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
    */

    /* 2
    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {

        log.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        // 2 seconds delay + 5 seconds sleep = 7 seconds delay (TOTAL)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    */

    /* 3
    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {

        log.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
    */

    // 4
    //@Scheduled(cron = "0 * * * * ?") // every minute
    @Scheduled(cron = "*/10 * * * * ?") // every 10 seconds
    public void scheduleTaskWithCronExpression() {

        log.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        log.info("Products in DB - {}", productService.getAllProducts().size());
    }
}
