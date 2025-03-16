package br.com.pomodoro.services;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LogServiceTest {

    private final LogService logService = new LogService();

    @Test
    void save() {
    }

    @Test
    void lastRecord() {
        var file = new File("./tmp/test.log");
        var result = this.logService.lastRecord(file);

        assertEquals("", result);
    }

    @Test
    void listAll() {
    }
}