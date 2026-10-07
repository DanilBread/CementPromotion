package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CompanyResultAggregator {
    public Map<String, CompanySummary> aggregate(List<Order> orders) {
        Objects.requireNonNull(orders, "Заказы не могут быть null");

        Map<String, CompanySummary> summaries = new HashMap<>();
        for (Order order : orders) {
            Objects.requireNonNull(order, "Заказ не может быть null");
            String company = order.getCompanyName();
            if (company == null || company.isBlank()){
                throw new IllegalArgumentException("Компания не может быть null или пустой строкой");
            }
            CompanySummary summary = summaries.get(company);
            if (summary == null){
                summary = new CompanySummary(company);
                summaries.put(company, summary);
            }
            summary.addOrder(order);
        }
        return summaries;
    }
}
