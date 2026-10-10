package com.cementpromotion.adapter.impl;

import com.cementpromotion.adapter.OrderSource;
import com.cementpromotion.model.Order;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public abstract class OrderReadAndSplit implements OrderSource {
    private final TxtReader reader = new TxtReader();
    private static final Logger log = LoggerFactory.getLogger(OrderReadAndSplit.class);

    public List<Order> read(String filename) {
        log.debug("Запуск чтения заказов из файла: {}, разделитель: {}", filename, getDelimiter());
        return readAndSplit(filename, getDelimiter());
    }

    public List<Order> readAndSplit(String fileName, String splitCharacter) {
        List<Order> orders = new ArrayList<>();
        List<String> lines = reader.loadLines((fileName));
        log.debug("Загружено {} строк из файла: {}", lines.size(), fileName);
        lines.stream()
                .filter(line -> {
                    boolean blank = line.isBlank();
                    if (blank) {
                        log.trace("Пропущена пустая строка в файле {}: ", fileName);
                    }
                    return !blank;
                })
                .forEach(line -> {
                    try {
                        String[] parts = line.split(splitCharacter);
                        if (parts.length != 3) {
                            log.error("Неверное количество полей ({} вместо 3) в строке '{}', файл: {}",
                                    parts.length, line, fileName);
                            throw new FileReadException("Неккоректный формат строки. Файл: " + fileName);
                        }
                        LocalDateTime dateTime = LocalDateTime.parse(parts[0].trim());
                        String companyName = parts[1].trim();
                        int quantity = Integer.parseInt(parts[2].trim());
                        orders.add(new Order(dateTime, companyName, quantity));
                        log.trace("Разобран заказ: {} | {} | {}", dateTime, companyName, quantity);

                    } catch (NumberFormatException | DateTimeException e) {
                        log.error("Ошибка парсинга строки '{}', файл: {}", line, fileName, e);
                        throw new FileReadException("Ошибка парсинга строки. Файл: " + fileName, e);
                    }
                });

        log.info("Прочитано {} заказов из файла: {}", orders.size(), fileName);
        return orders;
    }

    public abstract String getDelimiter();

}

