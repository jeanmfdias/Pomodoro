package br.com.pomodoro.domains.user;

import br.com.pomodoro.domains.time.Time;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class UserService {
    public User create() {
        String name = JOptionPane.showInputDialog(null, "Type the name:");
        String email = JOptionPane.showInputDialog(null, "Type the email:");
        return new User(name, email);
    }

    public void getAll(Map<User, List<Time>> base) {
        String message = "";
        for (Map.Entry<User, List<Time>> entry : base.entrySet()) {
            message += "%s (%s)\n".formatted(entry.getKey().getName(), entry.getKey().getEmail());
        }
        JOptionPane.showMessageDialog(null, message, "Pomodoro - Users",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
