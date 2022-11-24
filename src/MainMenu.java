import SnakeGame.Snake;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MainMenu extends JFrame{

    private JPanel panel1;
    private JButton feelingBoredButton;
    JMenu fileMenu, toolsMenu, rentMenu, userMenu;
    JMenuItem item=null, addUser, amendUser, removeUser, viewUsers;
    ArrayList<Tool> allTools = new ArrayList<>();
    ArrayList<Rental> allRentals = new ArrayList<>();
    ArrayList<User> allUsers = new ArrayList<>();


    public MainMenu(){
        UIManager.put("MenuItem.selectionBackground", Color.orange);   //https://community.oracle.com/tech/developers/discussion/1369819/color-of-item-selected-in-jmenu
        Font f = new Font("sans-serif", Font.PLAIN, 17);
        UIManager.put("MenuItem.font", f);
        UIManager.put("Menu.font", f);                                  //https://stackoverflow.com/questions/41364080/swing-change-menu-bar-and-menu-items-font-size-in-runtime
        setContentPane(panel1);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());
        setSize(600,600);

        Toolkit toolkit = getToolkit();                                                         //https://www.youtube.com/watch?v=pbDbnmlFTS0
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Users.data"));
            this.allUsers = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error in retrieving user data", "", JOptionPane.ERROR_MESSAGE);
        }

        createFileMenu();
        createToolsMenu();
        createRentMenu();

        userMenu = new JMenu("Users");

        addUser = new JMenuItem("Add User");
        addUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser(allUsers);
            }
        });

        amendUser = new JMenuItem("Amend User");
        amendUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser(allUsers);
            }
        });

        removeUser = new JMenuItem("Remove User");
        removeUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeUser(allUsers);
            }
        });

        viewUsers = new JMenuItem("View Users");
        viewUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewUsers(allUsers);
            }
        });

        userMenu.add(addUser);
        userMenu.add(amendUser);
        userMenu.add(removeUser);
        userMenu.add(viewUsers);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        menuBar.add(rentMenu);
        menuBar.add(userMenu);

        addTestTools();

        setVisible(true);

        feelingBoredButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    JFrame ex = new Snake();
                    ex.setVisible(true);
                });
            }
        });
    }
    private void createFileMenu(){

        fileMenu = new JMenu("File");

        String[] itemNames = {"Log Out", "Exit System"};

        for(int i=0;i<itemNames.length;i++){
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this::actionPerformed);

            if(i==1)
                fileMenu.addSeparator();

            fileMenu.add(item);
        }
    }
    private void createToolsMenu(){

        toolsMenu = new JMenu("Tools");

        String[] itemNames = {"Add","Amend","Remove","View"};

        for(int i=0;i<itemNames.length;i++) {
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this::actionPerformed);
            toolsMenu.add(item);
        }
    }
    private void createRentMenu(){

        rentMenu = new JMenu("Rent");

        String[] itemNames = {"Add Rental","Return Rental","View Rentals"};

        for(int i=0;i<itemNames.length;i++) {
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this::actionPerformed);
            rentMenu.add(item);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int choice;
        if(e.getActionCommand().equals("Exit System")) {
            choice = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit the system?",
                    "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,"Exiting... Goodbye :)",
                        "",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
        else if (e.getActionCommand().equals("Log Out")) {
            choice = JOptionPane.showConfirmDialog(null, "Are you sure you wish to log out?",
                    "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,"Logging out...",
                        "",JOptionPane.INFORMATION_MESSAGE);
                try {
                    new Login();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        }

        else if(e.getActionCommand().equals("Add"))
            addTool();
        else if(e.getActionCommand().equals("Amend"))
            amendTool(allTools);
        else if(e.getActionCommand().equals("Remove"))
            removeTool(allTools);
        else if(e.getActionCommand().equals("View"))
            viewTools(allTools);

        else if(e.getActionCommand().equals("Add Rental"))
            if (!allTools.isEmpty()) {
                addRental(allTools, allRentals);
            }
            else JOptionPane.showMessageDialog(null,"There are no tools recorded on the system");

        else if(e.getActionCommand().equals("Return Rental"))
            returnRental(allTools, allRentals);

        else if(e.getActionCommand().equals("View Rentals"))
            viewRentals(allRentals);

    }

    public void addUser(ArrayList<User> allUsers){
        String username, password;

        /*int lastHighestID = 0;
        for (User user : allUsers){
            if (user.getID() > lastHighestID){
                lastHighestID = user.getID();
                User.setCounter(lastHighestID);
            }
        }

        Iterator<User> iterator = allUsers.iterator();
        User user;
        boolean b = iterator.hasNext();
        while (b){
            user = iterator.next();
                if (user.getID() > lastHighestID){
                    lastHighestID = user.getID();
                    User.setCounter(lastHighestID);
                    b = false;
                }
        }*/

        username = JOptionPane.showInputDialog("Please enter a username");
            if (username!=null){
                while (!isvalidUserName(username, allUsers)){
                    username = JOptionPane.showInputDialog("Please enter a username");
                }
                password = JOptionPane.showInputDialog("Please enter a password");
                if (password!=null){
                    while (!isValidPass(password)){
                        password = JOptionPane.showInputDialog("Please enter a password");
                    }
                    User user1 = new User(username, password);
                    allUsers.add(user1);
                    JOptionPane.showMessageDialog(null, "\"" + username + "\" has been added to the system users");
                    updateUsers();
                }

            }
    }
    public void removeUser(ArrayList<User> allUsers){
        User user;

        JTable tbl = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] headers = new String[]{"User ID", "Username", "Password" };

        dtm.setColumnIdentifiers(headers);
        tbl.setModel(dtm);

        JScrollPane pane = new JScrollPane(tbl);

        Iterator<User> iterator = allUsers.iterator();

        while (iterator.hasNext()){
            user = iterator.next();
            if(user != null){
                dtm.addRow(new String[]{String.valueOf(user.getID()),user.getUsername(), user.getPassword()});       //https://stackoverflow.com/questions/22371720/how-to-add-row-dynamically-in-jtable
            }
        }
        if (!allUsers.isEmpty()) {
            tbl.setEnabled(false);          //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
            String removeIDAsString;
            removeIDAsString = JOptionPane.showInputDialog(null, pane, "Enter the user ID of the user you wish to remove");
            if (removeIDAsString !=null){
                while (!isValidSearchID(removeIDAsString)){
                    removeIDAsString = JOptionPane.showInputDialog(null, pane, "Enter the user ID of the user you wish to remove");
                }
                int removeID = Integer.parseInt(removeIDAsString);

                User userToRemove = null;
                for (User user1 : allUsers){
                    if (user1.getID() == removeID){
                        userToRemove = user1;
                    }
                }

                int choice = JOptionPane.showConfirmDialog(null, "The details of your selected user are:" + "\n\n" + userToRemove + "\n\nAre you sure you wish to remove this user?");
                if (choice == JOptionPane.YES_OPTION) {
                    allUsers.remove(userToRemove);
                    updateUsers();
                    JOptionPane.showMessageDialog(null, "The user has been removed from the system.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
    }
    public void viewUsers(ArrayList<User> allUsers){
        User user;

        JTable tbl = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] headers = new String[]{"User ID", "Username", "Password" };

        dtm.setColumnIdentifiers(headers);
        tbl.setModel(dtm);

        JScrollPane pane = new JScrollPane(tbl);

        Iterator<User> iterator = allUsers.iterator();

        while (iterator.hasNext()){
            user = iterator.next();
            if(user != null){
                dtm.addRow(new String[]{String.valueOf(user.getID()),user.getUsername(), user.getPassword()});       //https://stackoverflow.com/questions/22371720/how-to-add-row-dynamically-in-jtable
            }
        }
        if (!allUsers.isEmpty()) {
            tbl.setEnabled(false);          //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
            JOptionPane.showMessageDialog(null, pane, "User List", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "There are no users on the system");
        }
    }

    public void updateUsers(){
        try {
            File file = new File("Users.data");
            file.delete();                                                  //https://www.w3schools.com/java/java_files_delete.asp
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Users.data"));
            objectOutputStream.writeObject(this.allUsers);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTool(){
        String toolType, toolManufacturer, toolDesc, toolRateAsString;
        float toolRate;

        String[] toolTypeOptions = {"Drill", "Cement Mixer", "Sander", "Rotavator", "Ladder", "Floor Saw", "Digger", "Welder", "Compactor"};

        toolType = (String) JOptionPane.showInputDialog(null, "Please select the type of tool:", "Tool Type", JOptionPane.QUESTION_MESSAGE, null, toolTypeOptions, toolTypeOptions[1]);
        if (toolType != null){
                toolManufacturer = JOptionPane.showInputDialog("Please enter the manufacturer of the tool:");
                if (toolManufacturer != null){
                    while (!isValidManufacturer(toolManufacturer)) {
                        toolManufacturer = JOptionPane.showInputDialog("Please enter the manufacturer of the tool:");
                    }
                    toolDesc = JOptionPane.showInputDialog("Please enter the tool's description");
                    if (toolDesc != null){
                        while (!isValidDescription(toolDesc)) {
                            toolDesc = JOptionPane.showInputDialog("Please enter the tool's description");
                        }
                        toolRateAsString = JOptionPane.showInputDialog("Please enter the tool rate:");
                        if (toolRateAsString != null){
                            while (!isValidRate(toolRateAsString)){
                                toolRateAsString = JOptionPane.showInputDialog("Please enter the tool rate:");
                            }
                            if (!toolRateAsString.isEmpty()){
                                toolRate = Float.parseFloat(toolRateAsString);
                                Tool tool = new Tool(toolType, toolManufacturer, toolRate, toolDesc);
                                allTools.add(tool);
                                JOptionPane.showMessageDialog(null, "Tool has been added to the system!");
                            }
                        }
                    }
                }
            }
        }
    public void viewTools(ArrayList<Tool> allTools){

        Tool tool;

        JTable tbl = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] headers = new String[]{ "Tool ID", "Tool Type", "Manufacturer", "Description", "Rate", "Status" };

        dtm.setColumnIdentifiers(headers);
        tbl.setModel(dtm);

        Iterator<Tool> iterator = allTools.iterator();

        JScrollPane pane = new JScrollPane(tbl);

        while (iterator.hasNext()){
            tool = iterator.next();
            if(tool != null){
                dtm.addRow(new String[]{String.valueOf(tool.getId()), tool.getToolType(), tool.getToolManufacturer(), tool.getToolDesc(), String.valueOf(tool.getToolRate()), tool.getToolStatus()});       //https://stackoverflow.com/questions/22371720/how-to-add-row-dynamically-in-jtable
            }
        }
        if (!allTools.isEmpty()) {
            tbl.setEnabled(false);          //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
            JOptionPane.showMessageDialog(null, pane, "Tool List", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "There are currently no tools recorded on the system.", "Tool List", JOptionPane.INFORMATION_MESSAGE);
    }
    public void amendTool(ArrayList<Tool> allTools) {
        JTable tbl = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] headers = new String[]{ "Tool ID", "Tool Type", "Manufacturer", "Description", "Rate", "Status" };

        dtm.setColumnIdentifiers(headers);
        tbl.setModel(dtm);
        JScrollPane pane = new JScrollPane(tbl);

        ArrayList<Tool> amendableTools = new ArrayList<>();
        for (Tool tool : allTools) {
            if (tool.getToolStatus().equals("IN")||tool.getToolStatus().equals("UNAVAILABLE")) {
                amendableTools.add(tool);
            }
        }
        boolean toolsFound = false;

        String search = JOptionPane.showInputDialog("Please enter a search phrase");
        if (search != null) {
            for (Tool tool : amendableTools) {
                if (tool.getId() == Integer.parseInt(search) || tool.getToolType().equalsIgnoreCase(search) || tool.getToolManufacturer().equalsIgnoreCase(search) || tool.getToolDesc().equalsIgnoreCase(search) || tool.getToolStatus().equalsIgnoreCase(search)) {
                    toolsFound = true;
                    dtm.addRow(new String[]{String.valueOf(tool.getId()), tool.getToolType(), tool.getToolManufacturer(), tool.getToolDesc(), String.valueOf(tool.getToolRate()), tool.getToolStatus()});       //https://stackoverflow.com/questions/22371720/how-to-add-row-dynamically-in-jtable
                }
            }
            if (toolsFound) {
                tbl.setEnabled(false);
                int searchID = 0;
                String searchIDAsString = JOptionPane.showInputDialog(null, pane, "Enter the tool ID of the tool you wish to amend here");
                if (searchIDAsString!=null){
                    while (!isValidSearchID(searchIDAsString)){
                        searchIDAsString = JOptionPane.showInputDialog(null, pane, "Enter the tool ID of the tool you wish to amend here");
                    }
                    searchID = Integer.parseInt(searchIDAsString);
                }
                Tool toolToAmend;
                for (Tool tool : amendableTools) {
                    if (tool.getId() == searchID) {
                        toolToAmend = tool;
                        new AmendTool(toolToAmend);
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(null, "No matches found", "Search results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void removeTool(ArrayList<Tool> allTools){
        ArrayList<Tool> removableTools = new ArrayList<>();
        for (Tool tool : allTools) {
            if (tool.getToolStatus().equals("IN")||tool.getToolStatus().equals("UNAVAILABLE")) {
                removableTools.add(tool);
            }
        }

        StringBuilder searchResults = new StringBuilder();

        String search = JOptionPane.showInputDialog("Please enter a search phrase:");
        if (search!=null) {
            for (Tool tool : removableTools) {
                if (tool.getId() == Integer.parseInt(search) || tool.getToolType().equalsIgnoreCase(search) || tool.getToolManufacturer().equalsIgnoreCase(search) || tool.getToolDesc().equalsIgnoreCase(search) || tool.getToolStatus().equalsIgnoreCase(search)) {
                    searchResults.append(tool).append("\n");
                }
            }
            if (!searchResults.isEmpty()) {
                int searchID = 0;
                String searchIDAsString = JOptionPane.showInputDialog("The following tools match your search:\n\n" + searchResults + "\n\nEnter the id of the tool you wish to remove.");
                if (searchIDAsString != null) {
                    while (!isValidSearchID(searchIDAsString)) {
                        searchIDAsString = JOptionPane.showInputDialog("The following tools match your search:\n\n" + searchResults + "\n\nEnter the id of the tool you wish to remove.");
                    }
                    searchID = Integer.parseInt(searchIDAsString);
                    Tool toolToRemove = null;
                    for (Tool tool : allTools) {
                        if (tool.getId() == searchID) {
                            toolToRemove = tool;
                        }
                    }
                    int choice = JOptionPane.showConfirmDialog(null, "The details of your selected tool are:" + "\n\n" + toolToRemove + "\n\nAre you sure you wish to remove this tool?");
                    if (choice == JOptionPane.YES_OPTION) {
                        allTools.remove(toolToRemove);
                        JOptionPane.showMessageDialog(null, "The tool has been removed from the system.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(null, "No matches found", "Search results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void addRental(ArrayList<Tool> allTools, ArrayList<Rental> allRentals){
        new Rent(allTools, allRentals);
    }
    public void returnRental(ArrayList<Tool> allTools, ArrayList<Rental> allRentals) {
        ArrayList<Tool> returnableTools = new ArrayList<>();
        for (Tool tool : allTools) {
            if (tool.getToolStatus().equals("OUT")) {
                returnableTools.add(tool);
            }
        }

        StringBuilder searchResults = new StringBuilder();

        String search = JOptionPane.showInputDialog("Please enter a search phrase:");
        if (search != null){
            for (Tool tool : returnableTools) {
                if (tool.getToolType().equalsIgnoreCase(search) || tool.getToolManufacturer().equalsIgnoreCase(search) || tool.getToolDesc().equalsIgnoreCase(search) || tool.getToolStatus().equalsIgnoreCase(search)) {
                    searchResults.append(tool).append("\n");
                }
            }
            if (!searchResults.isEmpty()) {
                int removeID = Integer.parseInt(JOptionPane.showInputDialog("The following tools match your search:\n\n" + searchResults + "\n\nEnter the id of the tool you wish to return."));
                Tool toolToReturn = null;
                for (Tool tool : allTools) {
                    if (tool.getId() == removeID) {
                        toolToReturn = tool;
                    }
                }
                int choice = JOptionPane.showConfirmDialog(null, "The details of your selected tool are:" + "\n\n" + toolToReturn + "\n\nAre you sure you wish to return this tool?");
                if (choice == JOptionPane.YES_OPTION) {
                    toolToReturn.setToolStatus("IN");
                    for (Rental rental : allRentals) {
                        if (rental.getToolID() == removeID)
                            allRentals.remove(rental);
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(null, "No matches found", "Search results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void viewRentals(ArrayList<Rental> allRentals){

        Rental rental;

        JTable tbl = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] headers = new String[]{ "Customer Name", "Email", "Phone", "Eircode", "Rented Tool ID" };

        dtm.setColumnIdentifiers(headers);

        tbl.setModel(dtm);

        Iterator<Rental> iterator = allRentals.iterator();
        JScrollPane pane = new JScrollPane(tbl);                            //https://stackoverflow.com/questions/2320812/jtable-wont-show-column-headers
        while (iterator.hasNext()){
            rental = iterator.next();
            if(rental != null){
                dtm.addRow(new String[]{rental.getCustName(), rental.getEmail(), String.valueOf(rental.getPhone()), rental.getEircode(), String.valueOf(rental.getToolID())});       //https://stackoverflow.com/questions/22371720/how-to-add-row-dynamically-in-jtable
            }
        }
        if (!allRentals.isEmpty()) {
            tbl.setEnabled(false);
            JOptionPane.showMessageDialog(null, pane, "Rental List", JOptionPane.INFORMATION_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "There are no rentals recorded on the system");
    }


    public static void main(String[] args) {
        new MainMenu();     //test run
    }

    public void addTestTools(){
        Tool t1 = new Tool("Drill", "DeWalt", 20, "This is a drill");
        Tool t2 = new Tool("Jigsaw", "DeWalt", 50, "This is a jigsaw");
        Tool t3 = new Tool("Cement Mixer", "DeWalt", 70, "This is a cement mixer");

        allTools.add(t1);
        allTools.add(t2);
        allTools.add(t3);
    }

    //********************Validation Methods***********************


    public boolean isvalidUserName(String username, ArrayList<User> allUsers){
        boolean result = false;
            if (!username.isEmpty()){
                for (User user : allUsers){
                    if (user.getUsername().equals(username)){
                        result = false;
                        JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    else result = true;
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "A username was not entered", "Error", JOptionPane.ERROR_MESSAGE);
            }
        return result;
    }

    public boolean isValidPass(String password){
        boolean result;
        if (!password.isEmpty()){
            if (password.length() >= 5){
                result = true;
            }
            else {
                result = false;
                JOptionPane.showMessageDialog(null, "Password must be at least 5 characters long", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            result = false;
            JOptionPane.showMessageDialog(null, "A password was not entered", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public boolean isValidManufacturer(String manufacturer){
        boolean result;
        if (!manufacturer.isEmpty()){
            result = true;
        }
        else {
            result = false;
            JOptionPane.showMessageDialog(null, "Tool manufacturer was not entered", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }
    public boolean isValidDescription(String description){
        boolean result;
        if (!description.isEmpty()){
            if (description.length() <= 20){
                result = true;
            }
            else {
                result = false;
                JOptionPane.showMessageDialog(null, "Tool description must not exceed 20 characters", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            result = false;
            JOptionPane.showMessageDialog(null, "Tool description was not entered", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
    public boolean isValidRate(String rate){
        boolean result = false;

        if (!rate.isEmpty()){
            for (int i = 0; i < rate.length(); i++){
                if (!Character.isDigit(rate.charAt(i))){
                    result = false;
                    JOptionPane.showMessageDialog(null, "Invalid Rate", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                else {
                    result = true;
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Rate was not entered", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    public boolean isValidSearchID(String searchID){
        boolean result = false;

        if (!searchID.isEmpty()){
            for (int i = 0; i < searchID.length(); i++){
                if (!Character.isDigit(searchID.charAt(i))){
                    result = false;
                    JOptionPane.showMessageDialog(null, "Invalid Search ID", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                else {
                    result = true;
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Search ID was not entered", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }
}
