package com.cementpromotion.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class CompanyResultAggregator {
    private static final Logger log = LoggerFactory.getLogger(CompanyResultAggregator.class);
    public Map<String, CompanySummary> aggregate(List<Order> orders) {
        Objects.requireNonNull(orders, "Заказы не могут быть null");
        log.debug("Начало агрегации: получено {} заказов", orders.size());

        Map<String, CompanySummary> summaries = new HashMap<>();
        for (Order order : orders) {
            Objects.requireNonNull(order, "Заказ не может быть null");
            String company = order.getCompanyName();
            if (company == null || company.isBlank()){
                log.error("Компания не может быть null или пустой строкой,  заказ: {}", order);
                throw new IllegalArgumentException("Компания не может быть null или пустой строкой");
            }
            CompanySummary summary = summaries.get(company);
            if (summary == null){
                summary = new CompanySummary(company);
                summaries.put(company, summary);
                log.debug("Создана новая сводка для компании: {}", company);
            }
            summary.addOrder(order);
            log.debug("Заказ добавлен в сводку компании: {} : {}", company, order);
        }
        log.info("Агрегация завершена: {} заказов, {} компаний", orders.size(), summaries.size());
        return summaries;
    }
}
