package com.example.orders.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParserType {
    JSON(".json"), CSV(".csv");

    private final String fileExtension;
}
