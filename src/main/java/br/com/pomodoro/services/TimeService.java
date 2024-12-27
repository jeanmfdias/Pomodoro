package br.com.pomodoro.services;

import br.com.pomodoro.models.Time;
import br.com.pomodoro.models.User;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimeService {
    public User formUser(Object[] userListMenu) {
        int userChoice = JOptionPane.showOptionDialog(null, "Select an user", "Pomodoro - User",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userListMenu, 0);
        return (User) userListMenu[userChoice];
    }

    public Time formTime(Map<User, List<Time>> base) {
        List<Time> listTime = new ArrayList<>();
        for (Map.Entry<User, List<Time>> entry : base.entrySet()) {
            for (Time t : entry.getValue()) {
                if (t.getEndTimestamp() == 0) {
                    listTime.add(t);

                }
            }
        }
        Object[] timeListMenu = new Object[listTime.size()];
        int i = 0;
        for (Time t : listTime) {
            timeListMenu[i] = t.getDescription();
            i++;
        }
        int timeChoice = JOptionPane.showOptionDialog(null, "Select an time to close", "Pomodoro - Time",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, timeListMenu, 0);
        return listTime.get(timeChoice);
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

    public void listAll(Map<User, List<Time>> base) {
        String message = "List all tasks:\n";
        for (Map.Entry<User, List<Time>> entry : base.entrySet()) {
            message += entry.getValue() + "\n";
        }
        JOptionPane.showMessageDialog(null, message);
    }
}
