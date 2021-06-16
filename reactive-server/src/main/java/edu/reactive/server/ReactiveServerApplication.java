package edu.reactive.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ReactiveServerApplication.class})
public class ReactiveServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveServerApplication.class, args);
    }
}