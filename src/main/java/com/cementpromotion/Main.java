package com.cementpromotion;

import com.cementpromotion.adapter.OrderSource;
import com.cementpromotion.adapter.impl.UnifieldOrderSource;
import com.cementpromotion.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        OrderSource source = new UnifieldOrderSource();
        String fileName = "src/data/input/discount_day.txt";
        List<Order> orders = source.read(fileName);

        DiscountProcessor discountProcessor = new DiscountProcessor();
        BigDecimal beginPercent = new BigDecimal("50.0");
        BigDecimal stepPercent = new BigDecimal("5.0");
        BigDecimal price = new BigDecimal("10.0");

        discountProcessor.process(orders, price, beginPercent, stepPercent);

        CompanyResultAggregator aggregator = new CompanyResultAggregator();
        Map<String, CompanySummary> summaries = aggregator.aggregate(orders);

        ResultWriter output = new ResultWriter();
        output.printToConsole(summaries);
        output.writeToFile(summaries);

    }
}
