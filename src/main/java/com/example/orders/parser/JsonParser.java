package com.example.orders.parser;

import com.example.orders.model.Currency;
import com.example.orders.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonParser extends LineParser {

    private final ObjectMapper mapper;

    @Override
    protected Order parseLine(String line) throws Exception {
        var node = mapper.readTree(line);
        return new Order(
            node.get("orderId").asLong(),
            node.get("amount").decimalValue(),
            Currency.valueOf(
                node.get("currency").asText()
            ),
            node.get("comment").asText()
        );
    }

    @Override
    public ParserType getType() {
        return ParserType.JSON;
    }
}
