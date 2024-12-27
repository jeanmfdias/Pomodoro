package br.com.pomodoro.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class Time {
    private Long startTimestamp;
    private Long endTimestamp;
    private String description;
    private User user;

    public Time(Long startTimestamp, String description, User user) {
        this.startTimestamp = startTimestamp;
        this.description = description;
        this.user = user;
    }

    public Time() {

    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public Long getEndTimestamp() {
        return endTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
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
            Timestamp startTime = new Timestamp(this.startTimestamp);
            Timestamp endTime = new Timestamp(this.endTimestamp);
            Duration duration = Duration.between(startTime.toInstant(), endTime.toInstant());
            return duration.toHoursPart() + ":" + duration.toMinutesPart() + ":" + duration.toSecondsPart();
        }
        return "";
    }

    @Override
    public String toString() {
        return """
                --------------------------------------------------
                Task description: %s
                Start time (%s)
                    until (%s)
                    Total: %s
                User name: %s | e-mail: %s
                --------------------------------------------------
                """.formatted(
                this.description,
                this.getStartTimestampFormatted(),
                this.getEndTimestampFormatted(),
                this.getDuration(),
                this.user.getName(),
                this.user.getEmail());
    }
}
