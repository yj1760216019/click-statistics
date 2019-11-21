package com.carlinx.club;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClickStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickStatisticsApplication.class, args);
    }

}
