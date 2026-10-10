package com.cementpromotion.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Comparator;
import java.util.List;


public final class DiscountProcessor {
    private static final Logger log = LoggerFactory.getLogger(DiscountProcessor.class);

    public void process(List<Order> list, double price, double beginPercent, double stepPercent) {
        if (list == null || list.isEmpty()) {
            log.warn("Список заказов {}, скидки не применяются", list == null ? "null" : "пуст");
            return;
        }

        list.sort(Comparator.comparing(Order::getDateTime));
        log.debug("Начало расчёта скидок: заказов={}, цена={} , начальная скидка={}%, " +
                "шаг={}%", list.size(), price, beginPercent, stepPercent);
        Double percent = beginPercent;
        for (Order order : list) {
            order.setPrice(price);
            order.setSale(percent);
            log.trace("Заказ {}: цена={}, скидка={}%",order, price, percent);
            if (percent != 0.0) {
                percent -= stepPercent;
            }
        }
        log.info("Скидки применены к {} заказам", list.size());
    }
}
