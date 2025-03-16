package br.com.pomodoro;

import br.com.pomodoro.domains.time.Time;
import br.com.pomodoro.domains.user.User;
import br.com.pomodoro.domains.application.MenuService;

import javax.swing.*;
import java.util.*;

public class PomodoroApplication {
	public static void main(String[] args) {
		Map<User, List<Time>> base = new HashMap<>();
		int choice = -1;

		while (choice != 0) {
			choice = MenuService.menu();

			try {
				switch (choice) {
					case JOptionPane.CLOSED_OPTION -> choice = 0;
					case 0 -> MenuService.bye();
					case 1 -> base = MenuService.startTime(base);
					case 2 -> base = MenuService.endTime(base);
					case 3 -> MenuService.getLastTime(base);
					case 4 -> MenuService.getAllTime(base);
					case 5 -> base = MenuService.createUser(base);
					case 6 -> MenuService.getAllUSers(base);
					case 7 -> MenuService.saveLog(base);
					default -> throw new IllegalArgumentException("Invalid option!");
				}
			} catch (Throwable e) {
				JOptionPane.showMessageDialog(null, "%s\n%s".formatted(e.getMessage(), e.getClass()),
						"Pomodoro Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
