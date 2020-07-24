package hu.zerotohero.verseny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class VersenyApplication {

    public static void main(String[] args) {
        SpringApplication.run(VersenyApplication.class, args);
    }

}
