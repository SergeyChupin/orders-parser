package com.example.orders.model;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Getter
public class Order {
    private final long id;
    private final BigDecimal amount;
    private final Currency currency;
    private final String comment;

    public Order(long id, @NonNull BigDecimal amount,
                 @NonNull Currency currency, @NonNull String comment) {
        Assert.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, "Amount should be positive");
        Assert.hasText(comment, "Comment should be specified");
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }
}
