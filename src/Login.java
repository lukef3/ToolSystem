import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Login extends JFrame{
    private JPanel panel1;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JLabel lblLogin;

    ArrayList<User> allUsers = new ArrayList<>();

    public Login() throws IOException {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        /*****************************************************
         * Author: Zoran Davidovic
         * Site owner/sponsor: https://www.youtube.com
         * Availability: https://www.youtube.com/watch?v=cKmr-9Wc2u4
         *****************************************************/
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());

        setSize(300, 400);

        /*****************************************************
         * Author: Indra Subedi
         * Site owner/sponsor: //https://www.youtube.com
         * Availability: //https://www.youtube.com/watch?v=pbDbnmlFTS0
         *****************************************************/
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

        setVisible(true);

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Users.data"));
            this.allUsers = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error in retrieving user data", "", JOptionPane.ERROR_MESSAGE);
        }

        User admin = new User("admin", "admin");            //Hard Coded user details for testing
        allUsers.add(admin);

        txtUsername.setText(admin.getUsername());
        txtPassword.setText(admin.getPassword());

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(validLogin()){
                    JOptionPane.showMessageDialog(null, "Welcome " + txtUsername.getText());
                    txtUsername.setText("");
                    txtPassword.setText("");
                    dispose();
                    new MainMenu();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Shutting down... Goodbye :)");
                System.exit(0);
            }
        });
    }

    private boolean validLogin() {
        boolean result, containsUsername = false, containsPassword = false;
        String password = String.valueOf(txtPassword.getPassword());
        if (!txtUsername.getText().isEmpty()){
            if(!password.equals("")){
                for (User user : allUsers){
                    if (txtUsername.getText().equals(user.getUsername())){
                        containsUsername = true;
                        break;
                    }
                }
                if (containsUsername){
                    for (User user1 : allUsers){
                        if (password.equals(user1.getPassword())){
                            containsPassword = true;
                            break;
                        }
                    }
                    if (containsPassword){
                        result = true;
                    }
                    else {
                        result = false;
                        JOptionPane.showMessageDialog(null, "Invalid Password", "Invalid", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    result = false;
                    JOptionPane.showMessageDialog(null, "Invalid Username", "Invalid", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                result = false;
                JOptionPane.showMessageDialog(null, "You must enter a password!", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            result = false;
            JOptionPane.showMessageDialog(null, "You must enter a username!", "Invalid", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    public static void main(String[] args) {            //test run
        try {
            new Login();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
