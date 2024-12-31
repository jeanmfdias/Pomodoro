package br.com.pomodoro.services;

import br.com.pomodoro.contracts.services.LogInterface;

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.List;

public class LogService implements LogInterface {

    @Override
    public boolean save(String message, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append("\n" + message);
            writer.close();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public String lastRecord(File file) {
        return "";
    }

    @Override
    public List<String> listAll(File file) {
        return List.of();
    }
}
