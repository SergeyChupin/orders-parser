package com.example.orders.writer;

import org.springframework.stereotype.Component;

@Component
public class StdOutWriter implements Writer {

    @Override
    public void write(String line) {
        System.out.println(line);
    }
}
