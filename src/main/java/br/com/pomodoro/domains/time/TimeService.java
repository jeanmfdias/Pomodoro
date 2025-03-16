package br.com.pomodoro.domains.time;

import br.com.pomodoro.domains.user.User;
import br.com.pomodoro.services.LogService;

import javax.swing.*;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

public class TimeService {
    private LogService logService;

    public TimeService() {
        this.logService = new LogService();
    }

    public User formUser(Object[] userListMenu) {
        int userChoice = JOptionPane.showOptionDialog(null, "Select an user", "Pomodoro - User",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userListMenu, 0);
        return (User) userListMenu[userChoice];
    }

    public Time formTime(List<Time> listTime) {
        int i = 0;
        List<Time> availableTime = new ArrayList<>();
        for (Time t : listTime) {
            if (t.getEndTimestamp() == 0) {
                i++;
            }
        }
        Object[] timeListMenu = new Object[i];
        i = 0;
        for (Time t : listTime) {
            if (t.getEndTimestamp() == 0) {
                timeListMenu[i] = t.getDescription();
                availableTime.add(t);
                i++;
            }
        }
        if (i == 0) {
            throw new RuntimeException("Tasks not found!");
        }
        int timeChoice = JOptionPane.showOptionDialog(null, "Select an time to close", "Pomodoro - Time",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, timeListMenu, 0);
        return availableTime.get(timeChoice);
    }

    public Time create(User user) {
        Time time = new Time();

        String title = JOptionPane.showInputDialog("Type task title:");
        time.setUser(user);
        time.setDescription(title);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        time.setStartTimestamp(startTime.getTime());

        return time;
    }

    public String getAll(Map<User, List<Time>> base) {
        String message = "List all tasks:\n";
        for (Map.Entry<User, List<Time>> entry : base.entrySet()) {
            message += entry.getValue() + "\n";
        }
        return message;
    }

    public boolean saveLog(Map<User, List<Time>> base) {
        String messageToSave = "";
        List<Time> list;
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        String fileName = "./tmp/" + String.valueOf(timeStamp.getYear()) + String.valueOf(timeStamp.getMonth())
                + String.valueOf(timeStamp.getDay()) + "_pomodoro.log";
        File file = new File(fileName);
        boolean success = false;

        for (Map.Entry<User, List<Time>> entry : base.entrySet()) {
            list = entry.getValue();
            for (Time time : list) {
                messageToSave = time.getStartTimestamp() + ": " + time.getUser().getName() + ": "
                        + time.getDescription() + "(" + time.getDuration() + ")";
                success = this.logService.save(messageToSave, file);
                if (!success) {
                    break;
                }
            }
            if (!success) {
                break;
            }
        }
        return success;
    }
}
