package pl.coderslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.coderslab.thread.MessageReceiver;
import pl.coderslab.thread.MessageSender;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Bet24Application {

    public static void main(String[] args) {

//        SpringApplication.run(Bet24Application.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(Bet24Application.class, args);

        Thread messageSender1 = new Thread(context.getBean( MessageSender.class ));
        messageSender1.start();

        Thread messageReceiver1 = new Thread(context.getBean( MessageReceiver.class ));
        messageReceiver1.start();

    }
}
