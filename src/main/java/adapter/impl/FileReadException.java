package adapter.impl;

public class FileReadException extends RuntimeException {

    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadException(String message) {
        super(message);
    }
}
