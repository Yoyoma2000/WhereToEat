package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import model.*;
import model.event.Event;
import model.event.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.database.AddResTab;
import ui.tabs.database.FileTab;
import ui.tabs.database.HomeTab;
import ui.tabs.database.ListTab;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    // Global variables:
    public static final String JSON_STORE = "./data/database.json";
    public static final Location PLACEHOLDER_LOCATION = new Location("Somewhere", 0, 0);

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Location currLocation;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: creates new window for GUI of the program
    public WhereToEatUI() {
        super("Where To Eat?");
        setSize(WIDTH, HEIGHT);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        //setLayout(new BorderLayout());

        initializeDatabase();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.TOP);
        sidebar.setBackground(Color.GREEN);

        loadTabs();
        add(sidebar);
        setBackground(Color.GREEN);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                printLog();
                System.exit(0);
            }
        });
    }

    public Database getDatabase() {
        return database;
    }

    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    public Location getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(Location location) {
        currLocation = location;
    }

    //REQUIRES: nothing
    //MODIFIES: this, database
    //EFFECTS: ADD ALL DATA RELEATED THINGS HERE
    private void initializeDatabase() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        currLocation = PLACEHOLDER_LOCATION;
        database = new Database();
    }

    //REQUIRES: nothing
    //MODIFIES: this, HomeTab, ListTab, AddResTab, FileTab
    //EFFECTS: adds the multiple tabs relevant to database GUI
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

    // REQUIRES: nothing
    // EFFECTS: saves the database to file
    public void saveDataBase() {
        try {
            jsonWriter.open();
            jsonWriter.write(database);
            jsonWriter.close();
            System.out.println("Saved Database to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads database from file
    public void loadDataBase() {
        try {
            database = jsonReader.read();
            System.out.println("Loaded database from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        database.resetProcessedDataBase();
    }

    public void printLog() {
        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}
