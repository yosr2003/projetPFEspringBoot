package com.projetPfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjetPfeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetPfeApplication.class, args);
    }
}
