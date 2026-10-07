package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ResultWriter {
    private static final Path OUTPUT_DIR = Path.of("src", "data", "output");
    private static final String OUTPUT_FILE_NAME = "result.txt";

    public void printToConsole(Map<String, CompanySummary> summaries) {
        Objects.requireNonNull(summaries, "Сводка данных не должна быть пустой");
        for (CompanySummary summary : summaries.values()) {
            System.out.println(formatLine(summary));
        }
    }

    public void writeToFile(Map<String, CompanySummary> summaries) throws IOException {
        Objects.requireNonNull(summaries, "Сводка данных не должна быть пустой");
        Files.createDirectories(OUTPUT_DIR);
        Path outputFile = OUTPUT_DIR.resolve(OUTPUT_FILE_NAME);

        try (BufferedWriter writer = Files.newBufferedWriter(
                outputFile,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        )) {
            for (CompanySummary summary : summaries.values()) {
                writer.write(formatLine(summary));
                writer.newLine();
            }
        }
    }

    private String formatLine(CompanySummary summary) {
        return summary.getCompany() + " - " + formatMoney(summary.getFinalTotal()) + " рублей";
    }

    private String formatMoney(double value) {
        return String.format(Locale.US, "%.2f", value);
    }
}
