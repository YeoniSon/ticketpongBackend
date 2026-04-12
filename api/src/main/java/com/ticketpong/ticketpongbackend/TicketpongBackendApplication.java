package com.ticketpong.ticketpongbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TicketpongBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketpongBackendApplication.class, args);
    }

}
