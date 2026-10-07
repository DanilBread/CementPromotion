package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private String CompanyName;
    private  int CementQuantity;
    private LocalDateTime  dateTime;
    private double sale;
    private double price;


    public Order(LocalDateTime dateTime, String companyName, int cementQuantity) {
        this.dateTime = dateTime;
        CompanyName = companyName;
        CementQuantity = cementQuantity;
        sale = 0.0;
        price = 0.0;
    }

    public double getPrice() {
        return price;
    }

    public double getSale() {
        return sale;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public int getCementQuantity() {
        return CementQuantity;
    }

    public void setCementQuantity(int cementQuantity) {
        CementQuantity = cementQuantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

