package br.com.pomodoro;

import br.com.pomodoro.exceptions.LogException;
import br.com.pomodoro.models.Time;
import br.com.pomodoro.models.User;
import br.com.pomodoro.services.MenuService;
import br.com.pomodoro.services.TimeService;
import br.com.pomodoro.services.UserService;
import org.apache.logging.log4j.LoggingException;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.*;

public class PomodoroApplication {
	public static void main(String[] args) {
		TimeService service = new TimeService();
		UserService userService = new UserService();
		Object[] menu = MenuService.generate();
		Object[] userListMenu = null;
		Time time = new Time();
		User user;
		List<Time> listTime;
		Map<User, List<Time>> base = new HashMap<>();
		int choice = -1;

		while (choice != 0) {
			choice = JOptionPane.showOptionDialog(null, "Select an option", "Pomodoro Menu",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menu, 0);

			try {
				switch (choice) {
					case JOptionPane.CLOSED_OPTION:
						choice = 0;
					case 0:
						JOptionPane.showMessageDialog(null, "Bye!", "Pomodoro",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					case 1:
						user = service.formUser(userListMenu);
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
						time = service.formTime(base);
						if (time != null) {
							time.setEndTimestamp(endtime.getTime());
						} else {
							JOptionPane.showMessageDialog(null, "Time not found!",
									"Pomodoro Error", JOptionPane.ERROR_MESSAGE);
						}

						break;
					case 3:
						JOptionPane.showMessageDialog(null, time);
						break;
					case 4:
						service.listAll(base);
						break;
					case 5:
						user = userService.create();
						base.put(user, new ArrayList<>());
						userListMenu = MenuService.dynamicUserMenu(base);
						break;
					case 6:
						userService.list(base);
						break;
					case 7:
						boolean success = service.saveLog(base);
						if (success) {
							JOptionPane.showMessageDialog(null, "Log save with success!",
									"Pomodoro", JOptionPane.INFORMATION_MESSAGE);
						} else {
							throw new LogException("Error on save log!");
						}
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
