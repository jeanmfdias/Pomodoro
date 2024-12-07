public class Time {
    int startTimestamp;
    int endTimestamp;
    String description;
    User user;

    void showMessage() {
        System.out.println("Start Timestamp: " + startTimestamp);
        System.out.println("End Timestamp: " + endTimestamp);
        System.out.println("Description: " + description);
        System.out.println("User name: " + user.name);
    }
}
