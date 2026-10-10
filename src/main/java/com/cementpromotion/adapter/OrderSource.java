package com.cementpromotion.adapter;

import com.cementpromotion.model.Order;
import java.util.List;

public interface OrderSource {
    List<Order> read(String fileName);
}
