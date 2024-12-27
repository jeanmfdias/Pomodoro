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
		Object[] userListMenu = null;
		Object[] timeListMenu = null;
		Time time = new Time();
		User user = null;
		List<Time> listTime = new ArrayList<>();
		Map<User, List<Time>> base = new HashMap<>();
		int choice = -1;
		int userChoice = -1;
		int timeChoice = -1;
		int i = 0;

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
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userListMenu, 0);
					user = (User) userListMenu[userChoice];
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
					listTime = new ArrayList<>();
					for (Map.Entry<User, List<Time>> entry : base.entrySet()) {
						for (Time t : entry.getValue()) {
							if (t.getEndTimestamp() == 0) {
								listTime.add(t);

							}
						}
					}
					timeListMenu = new Object[listTime.size()];
					i = 0;
					for (Time t : listTime) {
						timeListMenu[i] = t.getDescription();
						i++;
					}
					timeChoice = JOptionPane.showOptionDialog(null, "Select an time to close", "Pomodoro - Time",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, timeListMenu, 0);
					time = listTime.get(timeChoice);

					Timestamp endtime = new Timestamp(System.currentTimeMillis());
					time.setEndTimestamp(endtime.getTime());

					break;
				case 3:
					JOptionPane.showMessageDialog(null, time);
					break;
				case 4:
					listTime = new ArrayList<>();
					for (Map.Entry<User, List<Time>> entry : base.entrySet()) {
                        listTime.addAll(entry.getValue());
					}
					service.listAll(listTime);
					break;
				case 5:
					user = userService.create();
					base.put(user, new ArrayList<>());
					userListMenu = MenuService.dynamicUserMenu(base);
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
