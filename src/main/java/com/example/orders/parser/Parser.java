package com.example.orders.parser;

import com.example.orders.model.FileLine;
import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface Parser {

    Flux<FileLine> parse(Path path);

    ParserType getType();
}
