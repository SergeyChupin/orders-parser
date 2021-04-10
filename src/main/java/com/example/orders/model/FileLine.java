package com.example.orders.model;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.Assert;

@Getter
public class FileLine {
    private static final String RESULT_OK = "OK";

    private final long number;
    private final String fileName;
    private final String result;
    private final Order order;

    private FileLine(long number, @NonNull String fileName,
                     @NonNull String result, Order order) {
        Assert.isTrue(number > 0, "Number should be positive");
        Assert.hasText(fileName, "File name should be specified");
        this.number = number;
        this.fileName = fileName;
        this.result = result;
        this.order = order;
    }

    public static FileLine ok(long number, String fileName, @NonNull Order order) {
        return new FileLine(number, fileName, RESULT_OK, order);
    }

    public static FileLine error(long number, String fileName, String error) {
        return new FileLine(number, fileName, error, null);
    }

    public boolean isOk() {
        return FileLine.RESULT_OK.equals(result);
    }
}
