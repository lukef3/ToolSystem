import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MainMenu extends JFrame{

    private JPanel panel1;
    JMenu fileMenu, toolsMenu, rentMenu;
    JMenuItem item=null;
    ArrayList<Tool> allTools = new ArrayList<>();
    public MainMenu(){
        UIManager.put("MenuItem.selectionBackground", Color.orange);   //https://community.oracle.com/tech/developers/discussion/1369819/color-of-item-selected-in-jmenu
        Font f = new Font("sans-serif", Font.PLAIN, 17);
        UIManager.put("MenuItem.font", f);
        UIManager.put("Menu.font", f);                                  https://stackoverflow.com/questions/41364080/swing-change-menu-bar-and-menu-items-font-size-in-runtime
        setContentPane(panel1);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());
        setLocationRelativeTo(null);

        createFileMenu();
        createToolsMenu();
        createRentMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        menuBar.add(rentMenu);

        addTestTools();

        setSize(800,600);
        setVisible(true);
    }

    private void createFileMenu(){

        fileMenu = new JMenu("File");

        String[] itemNames = {"New","Open","Save","Quit"};

        for(int i=0;i<itemNames.length;i++){
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this::actionPerformed);

            if(i==3)
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

        String[] itemNames = {"Add Rental","Return Rental","View Rental"};

        for(int i=0;i<itemNames.length;i++) {
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this::actionPerformed);
            rentMenu.add(item);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int choice;
        if(e.getActionCommand().equals("New"))
            JOptionPane.showMessageDialog(null,"Creating a new file to store bicycle details",
                    "New File",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Open"))
            JOptionPane.showMessageDialog(null,"Opening the file that stores bicycle details",
                    "Opening File",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Save"))
            JOptionPane.showMessageDialog(null,"Saving the file that stores bicycle details",
                    "Saving File",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Quit")) {
            choice = JOptionPane.showConfirmDialog(null, "Are you sure you wish to quit the application?",
                    "Exiting Application", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,"Saving the file that stores bicycle details before terminating",
                        "Saving File",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
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
            JOptionPane.showMessageDialog(null,"Adding rental...",
                    "Add Rental",JOptionPane.INFORMATION_MESSAGE);
    }









    public void addTool(){
        String toolType, toolManufacturer, toolDesc;
        float toolRate;

        toolType = JOptionPane.showInputDialog("Please enter tool type:");
        if (toolType != null) {
            while (toolType.isEmpty()) {
                toolType = JOptionPane.showInputDialog(null, "Please enter tool type:", "No tool type entered", JOptionPane.ERROR_MESSAGE);
            }
            while ()
        }
                /*
                toolManufacturer = JOptionPane.showInputDialog("Please enter the manufacturer of the tool:");
                if (toolManufacturer != null){
                    toolDesc = JOptionPane.showInputDialog("Please enter the tool's description");
                    if (toolDesc != null){
                        toolRate = Float.parseFloat(JOptionPane.showInputDialog("Please enter the tool rate:"));
                        Tool tool = new Tool(toolType, toolManufacturer, toolRate, toolDesc);
                        allTools.add(tool);
                        JOptionPane.showMessageDialog(null, "Tool has been added to the system!");
                    }
                }
            }*/
    }










    public void viewTools(ArrayList<Tool> allTools){

        StringBuilder allToolData = new StringBuilder();
        Tool tool;

        Iterator<Tool> iterator = allTools.iterator();

        while (iterator.hasNext()){
            tool = iterator.next();
            if(tool != null){
                allToolData.append(tool).append("\n");
            }
        }
        if (!allTools.isEmpty()) {
            JOptionPane.showMessageDialog(null, allToolData.toString(), "Tool List", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "There are currently no tools recorded on the system.", "Tool List", JOptionPane.INFORMATION_MESSAGE);
    }
    public void amendTool(ArrayList<Tool> allTools) {
        ArrayList<Tool> amendableTools = new ArrayList<>();
        for (Tool tool : allTools) {
            if (tool.getToolStatus().equals("IN")||tool.getToolStatus().equals("UNAVAILABLE")) {
                amendableTools.add(tool);
            }
        }

        StringBuilder searchResults = new StringBuilder();

        String search = JOptionPane.showInputDialog("Please enter a search phrase:");
        for(Tool tool: amendableTools){
            if(tool.getToolType().equalsIgnoreCase(search) || tool.getToolManufacturer().equalsIgnoreCase(search) || tool.getToolDesc().equalsIgnoreCase(search) || tool.getToolStatus().equalsIgnoreCase(search)) {
                searchResults.append(tool).append("\n");
            }
        }
        if (!searchResults.isEmpty()) {
            int searchID = Integer.parseInt(JOptionPane.showInputDialog("The following tools match your search:\n\n" + searchResults + "\n\nEnter the id of the tool you wish the amend."));
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
    public void removeTool(ArrayList<Tool> allTools){
        ArrayList<Tool> removableTools = new ArrayList<>();
        for (Tool tool : allTools) {
            if (tool.getToolStatus().equals("IN")||tool.getToolStatus().equals("UNAVAILABLE")) {
                removableTools.add(tool);
            }
        }

        StringBuilder searchResults = new StringBuilder();

        String search = JOptionPane.showInputDialog("Please enter a search phrase:");
        for(Tool tool: removableTools){
            if(tool.getToolType().equalsIgnoreCase(search) || tool.getToolManufacturer().equalsIgnoreCase(search) || tool.getToolDesc().equalsIgnoreCase(search) || tool.getToolStatus().equalsIgnoreCase(search)) {
                searchResults.append(tool).append("\n");
            }
        }
        if (!searchResults.isEmpty()) {
        int removeID = Integer.parseInt(JOptionPane.showInputDialog("The following tools match your search:\n\n" + searchResults + "\n\nEnter the id of the tool you wish to remove."));
        Tool toolToRemove = null;
        for (Tool tool: allTools){
            if (tool.getId() == removeID){
                toolToRemove = tool;
            }
        }
        int choice = JOptionPane.showConfirmDialog(null, "The details of your selected tool are:" + "\n\n" + toolToRemove + "\n\nAre you sure you wish to remove this tool?");
        if (choice == JOptionPane.YES_OPTION) {
                allTools.remove(toolToRemove);
                JOptionPane.showMessageDialog(null, "The tool has been removed from the system.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "No matches found", "Search results", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

    public void addTestTools(){
        Tool t1 = new Tool("Drill", "DeWalt", 20, "This is a drill");
        Tool t2 = new Tool("Jigsaw", "DeWalt", 50, "This is a jigsaw");
        Tool t3 = new Tool("Cement Mixer", "DeWalt", 70, "This is a cement mixer");
        allTools.add(t1);
        allTools.add(t2);
        allTools.add(t3);
    }
}
