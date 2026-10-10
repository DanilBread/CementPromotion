package com.cementpromotion.adapter.impl;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public final class TxtOrderAdapter extends OrderReadAndSplit {
    private static final Logger log = LoggerFactory.getLogger(TxtOrderAdapter.class);

    @Override
    public String getDelimiter() {
        log.debug("Получен разделитель: '|'");
        return "\\|";
    }
}

