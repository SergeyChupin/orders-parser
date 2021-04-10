package com.example.orders.parser;

import com.example.orders.model.Currency;
import com.example.orders.model.Order;
import lombok.var;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;

@Component
public class CsvParser extends LineParser {

    @Override
    protected Order parseLine(String line) {
        var fields = line.split(",");
        return new Order(
            NumberUtils.parseNumber(fields[0], Long.class),
            NumberUtils.parseNumber(fields[1], BigDecimal.class),
            Currency.valueOf(fields[2]),
            fields[3]
        );
    }

    @Override
    public ParserType getType() {
        return ParserType.CSV;
    }
}
