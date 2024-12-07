public class Main {
    public static void main(String[] args) {
        Time time1 = new Time();
        User user1 = new User();

        user1.name = "Jean Dias";
        time1.user = user1;

        time1.showMessage();
    }
}
