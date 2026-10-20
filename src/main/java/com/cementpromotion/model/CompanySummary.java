package com.cementpromotion.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Setter
@Getter
public class CompanySummary {
    private final String company;
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    private int orderCount;
    private BigDecimal originalTotal;
    private BigDecimal discountTotal;
    private BigDecimal finalTotal;

    public CompanySummary(String company) {
        this.company = company;
        orderCount = 0;
        originalTotal = BigDecimal.ZERO;
        discountTotal = BigDecimal.ZERO;
        finalTotal = BigDecimal.ZERO;
    }

    public void addOrder(Order order) {
        orderCount++;
        BigDecimal orderTotal = order.getPrice()
                .multiply(BigDecimal.valueOf(order.getCementQuantity()));

        BigDecimal orderDiscount = orderTotal
                .multiply(order.getSale())
                .divide(HUNDRED, 2, RoundingMode.HALF_UP);

        originalTotal = originalTotal.add(orderTotal);
        discountTotal = discountTotal.add(orderDiscount);
        finalTotal = finalTotal.add(orderTotal.subtract(discountTotal));
    }

}
