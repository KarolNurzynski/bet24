package pl.coderslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.coderslab.jms.thread.MessageConsumingThread;
import pl.coderslab.jms.thread.MessageProducingThread;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Bet24Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Bet24Application.class, args);

        Thread messageSender1 = new Thread(context.getBean( MessageProducingThread.class ));
        messageSender1.start();

        Thread messageReceiver1 = new Thread(context.getBean( MessageConsumingThread.class ));
        messageReceiver1.start();

    }
}
