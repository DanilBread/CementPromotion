package com.cementpromotion.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Order {
    private String CompanyName;
    private  int CementQuantity;
    private LocalDateTime  dateTime;
    private BigDecimal sale;
    private BigDecimal price;


    public Order(LocalDateTime dateTime, String companyName, int cementQuantity) {
        this.dateTime = dateTime;
        CompanyName = companyName;
        CementQuantity = cementQuantity;
        sale = new BigDecimal("0.0");
        price = new BigDecimal("0.0");
    }

    @Override
    public String toString() {
        return "Order{" +
                "Компания: " + '\'' + CompanyName + '\'' +
                ", Количество цемента: " + CementQuantity + "т" +
                ", Дата: " + dateTime +
                ", Скидка: " + sale + "%" +
                ", Цена: " + price +
                '}';
    }
}

