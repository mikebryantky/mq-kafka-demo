package com.infy.mqkafkademo;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Message {
    private UUID id;
    private LocalDateTime transactionDateTime;
    private BigDecimal transactionAmount;

    public Message() {
        this.id = UUID.randomUUID();
        this.transactionDateTime = LocalDateTime.now();
        this.transactionAmount = generateRandomBigDecimalFromRange(
                BigDecimal.valueOf(-25000.00).setScale(2, RoundingMode.HALF_UP),
                new BigDecimal("25000.00").setScale(2, RoundingMode.HALF_UP)
        );
    }

    private BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(
                BigDecimal.valueOf(Math.random())
                        .multiply(max.subtract(min))
        );
        return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }
}
