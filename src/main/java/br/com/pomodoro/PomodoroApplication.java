package br.com.pomodoro;

import br.com.pomodoro.models.Time;
import br.com.pomodoro.models.User;
import br.com.pomodoro.services.MenuService;
import br.com.pomodoro.services.TimeService;
import br.com.pomodoro.services.UserService;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.*;

public class PomodoroApplication {
	public static void main(String[] args) {
		TimeService service = new TimeService();
		UserService userService = new UserService();
		Object[] menu = MenuService.generate();
		Object[] userList = null;
		Time time = new Time();
		User user = null;
		List<Time> listTime = new ArrayList<>();
		Map<User, List<Time>> base = new HashMap<>();
		int choice = -1;
		int userChoice = -1;

		while (choice != 0) {
			choice = JOptionPane.showOptionDialog(null, "Select an option", "Pomodoro Menu",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menu, 0);

			switch (choice) {
				case 0:
					JOptionPane.showMessageDialog(null, "Bye!", "Pomodoro",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					userChoice = JOptionPane.showOptionDialog(null, "Select an user", "Pomodoro - User",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userList, 0);
					user = (User) userList[userChoice];
					if (user != null) {
						time = service.create(user);
						listTime = base.computeIfAbsent(user, u -> new ArrayList<>());
						listTime.add(time);
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
					base.put(user, new ArrayList<>());
					userList = MenuService.dynamicUserMenu(base);
					break;
				case 6:
					userService.list(base);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Invalid option!", "Pomodoro Error",
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
