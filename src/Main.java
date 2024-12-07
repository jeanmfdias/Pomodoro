public class Main {
    public static void main(String[] args) {
        Time time1 = new Time();
        User user1 = new User();

        user1.setName("Jean Dias");
        time1.setUser(user1);

        time1.showMessage();
    }
}
