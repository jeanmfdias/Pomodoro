package br.com.pomodoro.pomodoro.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Time {
    private long startTimestamp;
    private long endTimestamp;
    private String description;
    private User user;

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showMessage() {
        System.out.println("--------------------------------------------------");
        System.out.println("Description: " + this.description);
        System.out.println("Start Timestamp: " + this.getStartTimestampFormatted());
        System.out.println("End Timestamp: " + this.getEndTimestampFormatted());
        System.out.println("--------------------------------------------------");
        System.out.println("User name: " + this.user.getName());
        System.out.println("User e-mail: " + this.user.getEmail());
        System.out.println("--------------------------------------------------");
        System.out.println("Duration: " + this.getDuration());
        System.out.println("--------------------------------------------------");
    }

    public String getStartTimestampFormatted() {
        if (this.startTimestamp != 0) {
            Timestamp time = new Timestamp(this.startTimestamp);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY - HH:mm:ss");
            return format.format(time);
        }
        return "";
    }

    public String getEndTimestampFormatted() {
        if (this.endTimestamp != 0) {
            Timestamp time = new Timestamp(this.endTimestamp);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY - HH:mm:ss");
            return format.format(time);
        }
        return "";
    }

    public String getDuration() {
        if (this.startTimestamp != 0 && this.endTimestamp != 0) {
            long diff = this.endTimestamp - this.startTimestamp;
            Timestamp time = new Timestamp(diff);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            return format.format(time);
        }
        return "";
    }
}
