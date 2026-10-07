package adapter.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class TxtReader {
    public List<String> loadLines(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл: " + fileName + e);
        }
    }
}
