package com.cementpromotion.model;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Slf4j
public final class DiscountProcessor {

    public void process(List<Order> list, BigDecimal price, BigDecimal beginPercent, BigDecimal stepPercent) {
        if (list == null || list.isEmpty()) {
            log.warn("Список заказов {}, скидки не применяются", list == null ? "null" : "пуст");
            return;
        }

        list.sort(Comparator.comparing(Order::getDateTime));
        log.debug("Начало расчёта скидок: заказов={}, цена={} , начальная скидка={}%, " +
                "шаг={}%", list.size(), price, beginPercent, stepPercent);
        BigDecimal percent = beginPercent;
        for (Order order : list) {
            order.setPrice(price);
            order.setSale(percent);
            log.trace("Заказ {}: цена={}, скидка={}%",order, price, percent);
            if (percent.signum() > 0) {
                percent = percent.subtract(stepPercent).max(BigDecimal.ZERO);
            }
        }
        log.info("Скидки применены к {} заказам", list.size());
    }
}
