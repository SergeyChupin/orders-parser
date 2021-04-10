package com.example.orders.parser;

import com.codepoetics.protonpack.StreamUtils;
import com.example.orders.model.FileLine;
import com.example.orders.model.Order;
import lombok.SneakyThrows;
import lombok.var;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

public abstract class LineParser implements Parser {

    @Override
    public Flux<FileLine> parse(Path path) {
        return Flux.using(
            () -> parseLines(path),
            Flux::fromStream,
            BaseStream::close
        );
    }

    @SneakyThrows
    private Stream<FileLine> parseLines(Path path) {
        var fileName = path.getFileName().toString();
        return StreamUtils.zipWithIndex(Files.lines(path))
            .map(line -> parseLine(fileName, line.getIndex() + 1, line.getValue()));
    }

    private FileLine parseLine(String fileName, long number, String line) {
        try {
            return FileLine.ok(number, fileName, parseLine(line));
        } catch (Exception e) {
            var error = e.getMessage();
            return FileLine.error(number, fileName, error != null ? error : "Unable parse line");
        }
    }

    protected abstract Order parseLine(String line) throws Exception;
}
