package adapter.impl;

import adapter.OrderSource;
import model.Order;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderReadAndSplit implements OrderSource {
    private final TxtReader reader = new TxtReader();

    public List<Order> read(String filename){
        return readAndSplit(filename, getDelimiter());
    }

    public List<Order> readAndSplit(String fileName, String splitCharacter) {
        List<Order> orders = new ArrayList<>();
        List<String> lines = reader.loadLines((fileName));
        lines.stream()
                .filter(line -> !line.isBlank())
                .forEach(line -> {
                    try {
                        String[] parts = line.split(splitCharacter);
                        if (parts.length != 3) {
                             throw new FileReadException("Неккоректный формат строки. Файл: " + fileName);
                        }
                        LocalDateTime dateTime = LocalDateTime.parse(parts[0].trim());
                        String companyName = parts[1].trim();
                        int quantity = Integer.parseInt(parts[2].trim());
                        orders.add(new Order(dateTime, companyName, quantity));

                    } catch (NumberFormatException | DateTimeException e) {
                        throw new FileReadException("Ошибка парсинга строки. Файл: " + fileName, e);
                    }
                });

        System.out.printf("Прочитано %d строк из файла: %s\n\n", orders.size(), fileName);
        return orders;
    }

    public abstract String getDelimiter();

}

