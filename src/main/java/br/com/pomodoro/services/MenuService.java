package br.com.pomodoro.services;

import br.com.pomodoro.models.Menu;

public class MenuService {
    public static Object[] generate() {
        Object[] menuItems = new Object[7];

        menuItems[0] = new Menu("Exit");
        menuItems[1] = new Menu("Start Time");
        menuItems[2] = new Menu("End Time");
        menuItems[3] = new Menu("Report Last Task");
        menuItems[4] = new Menu("Report All Tasks");
        menuItems[5] = new Menu("Add User");
        menuItems[6] = new Menu("List All Users");

        return menuItems;
    }
}
