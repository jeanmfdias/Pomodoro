package br.com.pomodoro.exceptions;

public class LogException extends Throwable {
    private final String message;

    public LogException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
