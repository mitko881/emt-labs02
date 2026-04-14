package mk.finki.ukim.mk.emtlabs02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmtLabs02Application {

    public static void main(String[] args) {
        SpringApplication.run(EmtLabs02Application.class, args);
    }
}