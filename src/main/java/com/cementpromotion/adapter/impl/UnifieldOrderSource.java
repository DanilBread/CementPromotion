package com.cementpromotion.adapter.impl;

import com.cementpromotion.adapter.OrderSource;
import com.cementpromotion.model.Order;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public final class UnifieldOrderSource implements OrderSource {
    private final TxtOrderAdapter txtAdapter = new TxtOrderAdapter();
    private final NoExtOrderAdapter noExtOrderAdapter = new NoExtOrderAdapter();

    @Override
    public List<Order> read(String fileName) {
        if (fileName.endsWith(".txt")) {
            log.debug("Файл: {} - определен как '.txt'", fileName);
            return txtAdapter.read(fileName);
        }
        log.debug("Файл: {} - определен как 'noext'", fileName);
        return noExtOrderAdapter.read(fileName);
    }
}
