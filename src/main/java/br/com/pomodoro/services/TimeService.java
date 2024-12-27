package br.com.pomodoro.services;

import br.com.pomodoro.models.Time;
import br.com.pomodoro.models.User;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeService {
    public Time create(User user) {
        Time time = new Time();

        String title = JOptionPane.showInputDialog("Type task title:");
        time.setUser(user);
        time.setDescription(title);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        time.setStartTimestamp(startTime.getTime());

        return time;
    }

    public void listAll(ArrayList<Time> list) {
        String message = "List all tasks:\n";
        for (Time time : list) {
            message += time + "\n";
        }
        JOptionPane.showMessageDialog(null, message);
    }
}
