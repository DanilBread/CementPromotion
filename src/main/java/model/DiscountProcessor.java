package model;

import java.util.Comparator;
import java.util.List;

public final class DiscountProcessor {
    public void process(List<Order> list, double price, double beginPercent, double stepPercent) {
        if (list == null || list.isEmpty()) {
            System.err.println("Лист заказов пустой!");
            return;
        }

        list.sort(Comparator.comparing(Order::getDateTime));
        Double percent = beginPercent;
        for (Order order : list) {
            order.setPrice(price);
            order.setSale(percent);
            if (percent != 0.0) {
                percent -= stepPercent;
            }
        }
    }
}
