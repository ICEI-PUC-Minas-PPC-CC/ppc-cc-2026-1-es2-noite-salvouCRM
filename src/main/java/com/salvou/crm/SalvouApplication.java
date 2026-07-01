package com.salvou.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Esta anotação ativa todas as proteções que lemos no pom.xml
public class SalvouApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalvouApplication.class, args);
    }
}
