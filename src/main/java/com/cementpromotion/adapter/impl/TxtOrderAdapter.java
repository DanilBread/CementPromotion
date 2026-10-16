package com.cementpromotion.adapter.impl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class TxtOrderAdapter extends OrderReadAndSplit {

    public TxtOrderAdapter(TxtReader reader) {
        super(reader);
    }

    @Override
    public String getDelimiter() {
        log.debug("Получен разделитель: '|'");
        return "\\|";
    }
}

