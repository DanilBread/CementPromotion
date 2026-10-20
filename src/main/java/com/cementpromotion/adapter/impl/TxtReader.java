package com.cementpromotion.adapter.impl;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public final class TxtReader {
    public List<String> loadLines(String fileName) {
        try {
            log.debug("Прочитан файл: {}", fileName);
            return Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException e) {
            log.error("Не удалось прочитать файл: {}", fileName);
            throw new FileReadException("Не удалось прочитать файл: " + fileName, e);
        }
    }
}
