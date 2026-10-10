package com.cementpromotion.model;


public class CompanySummary {
    private final String company;

    private int orderCount;
    private double originalTotal;
    private double discountTotal;
    private double finalTotal;

    public CompanySummary(String company) {
        this.company = company;
        orderCount = 0;
        originalTotal = 0.0;
        discountTotal = 0.0;
        finalTotal = 0.0;
    }

    public void addOrder(Order order) {
        orderCount++;
        double tempTotal = order.getPrice() * order.getCementQuantity();
        originalTotal += tempTotal;
        discountTotal = originalTotal * order.getSale() / 100;
        finalTotal = originalTotal - discountTotal;
    }

    public String getCompany() {
        return company;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public double getOriginalTotal() {
        return originalTotal;
    }

    public double getDiscountTotal() {
        return discountTotal;
    }

    public double getFinalTotal() {
        return finalTotal;
    }
}
