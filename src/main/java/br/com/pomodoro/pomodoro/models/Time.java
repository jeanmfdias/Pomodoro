package br.com.pomodoro.pomodoro.models;

public class Time {
    private int startTimestamp;
    private int endTimestamp;
    private String description;
    private User user;

    public int getStartTimestamp() {
        return startTimestamp;
    }

    public int getEndTimestamp() {
        return endTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public void setStartTimestamp(int startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public void setEndTimestamp(int endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showMessage() {
        System.out.println("Start Timestamp: " + startTimestamp);
        System.out.println("End Timestamp: " + endTimestamp);
        System.out.println("Description: " + description);
        System.out.println("br.pomodoro.models.User name: " + user.getName());
    }
}
