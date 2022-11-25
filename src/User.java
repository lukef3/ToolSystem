import java.io.Serializable;

//User.java

/**
 * An instantiable class which defines a User.
 * @author Luke Foley.
 */
public class User implements Serializable {
    /**
     * The ID of the user.
     */
    private int ID;
    /**
     * The username of the user.
     */
    private String username;
    /**
     * The password of the user.
     */
    private String password;
    /**
     * A static counter that will increase with every user created.
     * The counter is used to assign a unique ID to the user.
     */
    private static int counter;

    /**
     * User 2-argument constructor. Calls the 3 mutators and the incrementCounter
     * method to initialise the attributes of a User object with some user-supplied values.
     * These user-supplied values are validated before the constructor is called.The User's ID is
     * set using the value of the counter attribute, to ensure the User is assigned a unique ID.
     * @param username the username of the User.
     * @param password the password of the User.
     */
    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        incrementCounter();
        setID(counter);
    }

    /**
     * Method to increment the static counter attribute of the class. This ensures that every
     * User will have a unique ID.
     */
    private static void incrementCounter(){
        counter++;
    }

    /**
     * Sets the ID of this User to the value passed in.
     * @param ID the ID of the User.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Sets the username of this User to the value passed in.
     * @param username the username of the User.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of this User to the value passed in.
     * @param password the password of the User.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the static counter variable to the value passed in.
     * @param counter the static counter value of the class.
     */
    public static void setCounter(int counter) {
        User.counter = counter;
    }

    /**
     * Gets the ID of this User.
     * @return this User's ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the username of this User.
     * @return this User's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of this User.
     * @return this User's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to get the details of a User object.
     * @return a String value of all the User object's details.
     */
    @Override
    public String toString() {
        return "User ID: " + getID() + "\nUsername: " + getUsername() + "\nPassword: " + getPassword();
    }
}
