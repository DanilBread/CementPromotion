package com.cementpromotion.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultWriter {
    private static final Path OUTPUT_DIR = Path.of("src", "data", "output");
    private static final String OUTPUT_FILE_NAME = "result.txt";
    private static final Logger log = LoggerFactory.getLogger(ResultWriter.class);

    public void printToConsole(Map<String, CompanySummary> summaries) {
        Objects.requireNonNull(summaries, "Сводка данных не должна быть пустой");
        log.debug("Вывод в консоль : {} записей", summaries.size());
        for (CompanySummary summary : summaries.values()) {
            System.out.println(formatLine(summary));
        }
        log.info("Результат выведен в консоль: {} записей", summaries.size());
    }

    public void writeToFile(Map<String, CompanySummary> summaries) throws IOException {
        Objects.requireNonNull(summaries, "Сводка данных не должна быть пустой");
        Path outputFile = OUTPUT_DIR.resolve(OUTPUT_FILE_NAME);
        log.debug("Запись в файл {}: {} записей", outputFile.toAbsolutePath(), summaries.size());

        try {
            Files.createDirectories(OUTPUT_DIR);

            try (BufferedWriter writer = Files.newBufferedWriter(
                    outputFile,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE
            )) {
                for (CompanySummary summary : summaries.values()) {
                    String line = formatLine(summary);
                    writer.write(line);
                    writer.newLine();
                    log.debug("Записана строка: {}", line);
                }
            }
        } catch (IOException e) {
            log.error("Не удалось записать результат в файл: {}", outputFile.toAbsolutePath());
            throw e;
        }
        log.info("Результат записан в файл: {} : {} записей", outputFile.toAbsolutePath(), summaries.size());
    }

    private String formatLine(CompanySummary summary) {
        return summary.getCompany() + " - " + formatMoney(summary.getFinalTotal()) + " рублей";
    }

    private String formatMoney(double value) {
        return String.format(Locale.US, "%.2f", value);
    }
}
