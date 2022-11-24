import java.io.Serializable;

public class User implements Serializable {
    private int ID;
    private String username;
    private String password;
    private static int counter;

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        incrementCounter();
        setID(counter);
    }

    private static void incrementCounter(){
        counter++;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static void setCounter(int counter) {
        User.counter = counter;
    }
    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User ID: " + getID() + "\nUsername: " + getUsername() + "\nPassword: " + getPassword();
    }
}
