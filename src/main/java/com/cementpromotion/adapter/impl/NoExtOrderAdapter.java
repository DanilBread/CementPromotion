package com.cementpromotion.adapter.impl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class NoExtOrderAdapter extends OrderReadAndSplit {

    @Override
    public String getDelimiter() {
        log.debug("Получен разделитель: '#'");
        return "#";
    }
}

