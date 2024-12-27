package br.com.pomodoro;

import br.com.pomodoro.models.Time;
import br.com.pomodoro.models.User;
import br.com.pomodoro.services.MenuService;
import br.com.pomodoro.services.TimeService;
import br.com.pomodoro.services.UserService;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PomodoroApplication {
	public static void main(String[] args) {
		Object[] menu = MenuService.generate();
		int choice = -1;
		TimeService service = new TimeService();
		UserService userService = new UserService();
		Time time = new Time();
		ArrayList<Time> listTime = new ArrayList<>();
		User user = null;

		while (choice != 0) {
			choice = JOptionPane.showOptionDialog(null, "Select an option", "Pomodoro Menu",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menu, 0);

			switch (choice) {
				case 0:
					JOptionPane.showMessageDialog(null, "Bye!", "Pomodoro",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					if (user != null) {
						time = service.create(user);
					} else {
						JOptionPane.showMessageDialog(null, "User is required!",
								"Pomodoro Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 2:
					Timestamp endtime = new Timestamp(System.currentTimeMillis());
					time.setEndTimestamp(endtime.getTime());
					listTime.add(time);
					break;
				case 3:
					JOptionPane.showMessageDialog(null, time);
					break;
				case 4:
					service.listAll(listTime);
					break;
				case 5:
					user = userService.create();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Invalid option!", "Pomodoro Error",
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
