package br.com.pomodoro.services;

import br.com.pomodoro.contracts.services.LogInterface;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class LogService implements LogInterface {

    @Override
    public boolean save(String message, File file) {
        FileWriter writer;
        try {
            writer = new FileWriter(file, true);
            writer.append("\n" + message);
            writer.close();
            return true;
        } catch (IOException e) {
            try {
                File errorFile = new File("./tmp/error.log");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                writer = new FileWriter(errorFile, true);
                writer.append("\n" + timestamp.getTime() + ": " + e.getMessage());
                writer.close();
            } catch (IOException err) {
                throw new RuntimeException(err);
            }
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
