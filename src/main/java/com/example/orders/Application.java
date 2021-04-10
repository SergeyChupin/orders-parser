package com.example.orders;

import com.example.orders.service.ParserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Application.class)
            .getBean(ParserService.class)
            .parseFiles(args);
    }
}
