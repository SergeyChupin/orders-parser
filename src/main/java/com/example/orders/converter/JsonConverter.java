package com.example.orders.converter;

import com.example.orders.model.FileLine;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonConverter implements Converter {

    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public String convert(FileLine line) {
        var node = mapper.createObjectNode();

        if (line.isOk()) {
            node.put("id", line.getOrder().getId());
            node.put("amount", line.getOrder().getAmount());
            node.put("comment", line.getOrder().getComment());
        }

        node.put("filename", line.getFileName());
        node.put("line", line.getNumber());
        node.put("result", line.getResult());

        return mapper.writeValueAsString(node);
    }
}
