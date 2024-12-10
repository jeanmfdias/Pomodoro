package br.com.pomodoro.pomodoro.services;

import br.com.pomodoro.pomodoro.models.Time;
import br.com.pomodoro.pomodoro.models.User;

import java.sql.Timestamp;
import java.util.Scanner;

public class TimeService {
    public Time create() {
        Scanner reader = new Scanner(System.in);
        User user = new User();
        Time time = new Time();

        reader.reset();
        System.out.print("Type your name: ");
        String name = reader.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name is required!");
            return null;
        }

        reader.reset();
        System.out.print("Type your e-mail: ");
        String email = reader.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("Email is required!");
            return null;
        }

        user.setName(name);
        user.setEmail(email);

        reader.reset();
        System.out.print("Type task title: ");
        String title = reader.nextLine().trim();
        time.setUser(user);
        time.setDescription(title);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        time.setStartTimestamp(startTime.getTime());

        return time;
    }
}
