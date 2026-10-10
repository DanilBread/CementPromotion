package com.cementpromotion.adapter.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class TxtReader {
    private static Logger log = LoggerFactory.getLogger(TxtReader.class);
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
