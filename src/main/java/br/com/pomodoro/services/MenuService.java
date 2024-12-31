package br.com.pomodoro.services;

import br.com.pomodoro.models.Menu;
import br.com.pomodoro.models.Time;
import br.com.pomodoro.models.User;

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
}
