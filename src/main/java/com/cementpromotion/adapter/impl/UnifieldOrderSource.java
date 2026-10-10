package com.cementpromotion.adapter.impl;

import com.cementpromotion.adapter.OrderSource;
import com.cementpromotion.model.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class UnifieldOrderSource implements OrderSource {
    private final TxtOrderAdapter txtAdapter = new TxtOrderAdapter();
    private final NoExtOrderAdapter noExtOrderAdapter = new NoExtOrderAdapter();
    private static final Logger log = LoggerFactory.getLogger(UnifieldOrderSource.class);

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
