package com.example.orders.service;

import com.example.orders.converter.Converter;
import com.example.orders.model.FileLine;
import com.example.orders.parser.Parser;
import com.example.orders.parser.ParserType;
import com.example.orders.writer.Writer;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParserService {

    private final List<Parser> parsers;
    private final Converter converter;
    private final Writer writer;

    public void parseFiles(String[] fileNames) {
        Flux.fromArray(fileNames)
            .map(Paths::get)
            .filter(Files::isRegularFile)
            .filter(this::isParsableFile)
            .parallel()
            .runOn(Schedulers.parallel())
            .flatMap(this::parse)
            .map(converter::convert)
            .sequential()
            .doOnNext(writer::write)
            .blockLast();
    }

    private Flux<FileLine> parse(Path path) {
        var fileName = path.getFileName().toString();
        return parsers.stream()
            .filter(parser -> fileName.endsWith(parser.getType().getFileExtension()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Unable find parser for file: " + path))
            .parse(path);
    }

    private boolean isParsableFile(Path path) {
        var fileName = path.getFileName().toString();
        return Arrays.stream(ParserType.values())
            .anyMatch(type -> fileName.endsWith(type.getFileExtension()));
    }
}
