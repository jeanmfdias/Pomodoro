package br.com.pomodoro.contracts.services;

import java.io.File;
import java.util.List;

public interface LogInterface {
    public boolean save(String message, File file);

    public String lastRecord(File file);

    public List<String> listAll(File file);
}
