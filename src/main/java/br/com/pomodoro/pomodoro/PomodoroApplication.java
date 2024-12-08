package br.com.pomodoro.pomodoro;

import br.com.pomodoro.pomodoro.models.Time;
import br.com.pomodoro.pomodoro.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

@SpringBootApplication
public class PomodoroApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PomodoroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner reader = new Scanner(System.in);
		String formatterLimit = "##################################################";
		String menu = """
				%s
				0 - Exit
				1 - Start Time
				2 - End Time
				3 - Report Last Task
				Your choice:
				""".formatted(formatterLimit);
		int choice = -1;
		Time time = new Time();
		User user = new User();

		while (choice != 0) {
			System.out.print(menu);
			choice = reader.nextInt();

			switch (choice) {
				case 1:
					System.out.print("Type your name: ");
					String name = reader.nextLine();
					user.setName(name);

					System.out.print("Type your e-mail: ");
					String email = reader.nextLine();
					user.setEmail(email);

					System.out.println("Type task title: ");
					String title = reader.nextLine();
					time.setUser(user);
					time.setDescription(title);
					Timestamp startTime = new Timestamp(System.currentTimeMillis());
					time.setStartTimestamp(startTime.getTime());
					break;
				case 2:
					Timestamp endtime = new Timestamp(System.currentTimeMillis());
					time.setEndTimestamp(endtime.getTime());
					break;
				case 3:
					System.out.println(formatterLimit);
					time.showMessage();
					System.out.println(formatterLimit);
					break;
				default:
					System.out.println("Invalid option!");
			}
		}
	}
}
