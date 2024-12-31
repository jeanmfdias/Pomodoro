package br.com.pomodoro.services;

import br.com.pomodoro.exceptions.LogException;
import br.com.pomodoro.models.Menu;
import br.com.pomodoro.models.Time;
import br.com.pomodoro.models.User;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuService {
    public static Object[] generate() {
        Object[] menuItems = new Object[8];

        menuItems[0] = new Menu("Exit");
        menuItems[1] = new Menu("Start Time");
        menuItems[2] = new Menu("End Time");
        menuItems[3] = new Menu("Report Last Task");
        menuItems[4] = new Menu("Report All Tasks");
        menuItems[5] = new Menu("Add User");
        menuItems[6] = new Menu("List All Users");
        menuItems[7] = new Menu("Save Log");

        return menuItems;
    }

    public static Object[] dynamicUserMenu(Map<User, List<Time>> base) {
        Object[] newUsers = new Object[base.size()];
        int i = 0;
        for (Map.Entry<User, List<Time>> entry : base.entrySet()) {
            newUsers[i] = entry.getKey();
            i++;
        }
        return newUsers;
    }

    public static void bye() {
        JOptionPane.showMessageDialog(null, "Bye!", "Pomodoro",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static int menu() {
        return JOptionPane.showOptionDialog(null, "Select an option", "Pomodoro Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, MenuService.generate(), 0);
    }

    public static Map<User, List<Time>> startTime(Map<User, List<Time>> base) {
        TimeService timeService = new TimeService();
        Object[] userList = MenuService.dynamicUserMenu(base);
        User user = timeService.formUser(userList);
        if (user != null) {
            Time time = timeService.create(user);
            List<Time> listTime = base.computeIfAbsent(user, u -> new ArrayList<>());
            listTime.add(time);
        } else {
            throw new IllegalArgumentException("User is required!");
        }
        return base;
    }

    public static Map<User, List<Time>> endTime(Map<User, List<Time>> base) {
        Timestamp endtime = new Timestamp(System.currentTimeMillis());
        TimeService timeService = new TimeService();
        Object[] userList = MenuService.dynamicUserMenu(base);
        User user = timeService.formUser(userList);
        if (user != null) {
            List<Time> listTime = base.computeIfAbsent(user, u -> new ArrayList<>());
            Time time = timeService.formTime(user, listTime);
            if (time != null) {
                time.setEndTimestamp(endtime.getTime());
            } else {
                throw new IllegalArgumentException("Time not found!");
            }
        } else {
            throw new IllegalArgumentException("User is required!");
        }
        return base;
    }

    public static void getLastTime(Map<User, List<Time>> base) {
        TimeService timeService = new TimeService();
        Object[] userList = MenuService.dynamicUserMenu(base);
        User user = timeService.formUser(userList);
        if (user != null) {
            List<Time> timeList = base.computeIfAbsent(user, u -> new ArrayList<>());
            Time lastTime = timeList.getLast();
            JOptionPane.showMessageDialog(null, lastTime, "Pomodoro",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        throw new IllegalArgumentException("User is required!");
    }

    public static void getAllTime(Map<User, List<Time>> base) {
        TimeService timeService = new TimeService();
        String message = timeService.getAll(base);
        JOptionPane.showMessageDialog(null, message, "Pomodoro", JOptionPane.INFORMATION_MESSAGE);
    }

    public static Map<User, List<Time>> createUser() {
        UserService userService = new UserService();
        Map<User, List<Time>> base = new HashMap<>();
        User user = userService.create();
        base.put(user, new ArrayList<>());
        return base;
    }

    public static void getAllUSers(Map<User, List<Time>> base) {
        UserService userService = new UserService();
        userService.getAll(base);
    }

    public static void saveLog(Map<User, List<Time>> base) throws LogException {
        TimeService timeService = new TimeService();
        boolean success = timeService.saveLog(base);
        if (success) {
            JOptionPane.showMessageDialog(null, "Log save with success!",
                    "Pomodoro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            throw new LogException("Error on save log!");
        }
    }
}
