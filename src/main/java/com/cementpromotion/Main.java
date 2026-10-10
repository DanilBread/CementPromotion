package com.cementpromotion;

import com.cementpromotion.adapter.OrderSource;
import com.cementpromotion.adapter.impl.UnifieldOrderSource;
import com.cementpromotion.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        OrderSource source = new UnifieldOrderSource();
        String fileName = "src/data/input/discount_day.txt";
        List<Order> orders = source.read(fileName);

        DiscountProcessor discountProcessor = new DiscountProcessor();
        double beginPercent = 50.0;
        double stepPercent = 5.0;
        double price = 1000;
        discountProcessor.process(orders, price, beginPercent, stepPercent);

        CompanyResultAggregator aggregator = new CompanyResultAggregator();
        Map<String, CompanySummary> summaries = aggregator.aggregate(orders);

        ResultWriter output = new ResultWriter();
        output.printToConsole(summaries);
        output.writeToFile(summaries);

    }
}
