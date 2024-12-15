package br.com.pomodoro.pomodoro;

import br.com.pomodoro.pomodoro.models.Time;
import br.com.pomodoro.pomodoro.services.TimeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.util.ArrayList;
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
				0 - Exit
				1 - Start Time
				2 - End Time
				3 - Report Last Task
				4 - Report All Tasks
				Your choice:""".formatted(formatterLimit);
		int choice = -1;

		TimeService service = new TimeService();
		Time time = new Time();
		ArrayList<Time> listTime = new ArrayList<>();

		while (choice != 0) {
			System.out.print(menu);
			reader.reset();
			choice = reader.nextInt();

			switch (choice) {
				case 1:
					time = service.create();
					break;
				case 2:
					Timestamp endtime = new Timestamp(System.currentTimeMillis());
					time.setEndTimestamp(endtime.getTime());
					listTime.add(time);
					break;
				case 3:
					System.out.println(time);
					break;
				case 4:
					service.listAll(listTime);
					break;
				default:
					System.out.println("Invalid option!");
			}
			System.out.println(formatterLimit);
		}
	}
}
