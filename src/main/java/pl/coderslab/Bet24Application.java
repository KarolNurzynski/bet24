package pl.coderslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Bet24Application {

    public static void main(String[] args) {
        SpringApplication.run(Bet24Application.class, args);
    }
}
