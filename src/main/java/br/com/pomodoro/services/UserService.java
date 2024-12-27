package br.com.pomodoro.services;

import br.com.pomodoro.models.User;

import javax.swing.*;

public class UserService {
    public User create() {
        String name = JOptionPane.showInputDialog(null, "Type the name:");
        String email = JOptionPane.showInputDialog(null, "Type the email:");
        return new User(name, email);
    }
}
