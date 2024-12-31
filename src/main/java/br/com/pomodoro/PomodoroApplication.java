package br.com.pomodoro;

import br.com.pomodoro.models.Time;
import br.com.pomodoro.models.User;
import br.com.pomodoro.services.MenuService;

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
					case JOptionPane.CLOSED_OPTION:
						choice = 0;
					case 0:
						MenuService.bye();
						break;
					case 1:
						base = MenuService.startTime(base);
						break;
					case 2:
						base = MenuService.endTime(base);
						break;
					case 3:
						MenuService.getLastTime(base);
						break;
					case 4:
						MenuService.getAllTime(base);
						break;
					case 5:
						base = MenuService.createUser();
						break;
					case 6:
						MenuService.getAllUSers(base);
						break;
					case 7:
						MenuService.saveLog(base);
						break;
					default:
						throw new IllegalArgumentException("Invalid option!");
				}
			} catch (Throwable e) {
				JOptionPane.showMessageDialog(null, "%s\n%s".formatted(e.getMessage(), e.getClass()),
						"Pomodoro Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
