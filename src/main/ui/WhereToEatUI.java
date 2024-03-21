package ui;

import javax.swing.*;
import model.*;
import ui.tabs.*;

// GUI for WhereToEat
public class WhereToEatUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int List_TAB_INDEX = 1;
    public static final int Add_TAB_INDEX = 2;
    public static final int File_TAB_INDEX = 3;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;

    private Database database; // The main backbone

    public WhereToEatUI() {
        super("Where To Eat?");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        database = new Database();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.TOP);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    public Database getDatabase() {
        return database;
    }

    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel listTab = new ListTab(this);
        JPanel addResTab = new AddResTab(this);
        JPanel fileTab = new FileTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");

        sidebar.add(listTab, List_TAB_INDEX);
        sidebar.setTitleAt(List_TAB_INDEX, "List");

        sidebar.add(addResTab, Add_TAB_INDEX);
        sidebar.setTitleAt(Add_TAB_INDEX, "Add");

        sidebar.add(fileTab, File_TAB_INDEX);
        sidebar.setTitleAt(File_TAB_INDEX, "File");
    }
}
