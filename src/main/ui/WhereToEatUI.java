package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.database.AddResTab;
import ui.tabs.database.FileTab;
import ui.tabs.database.HomeTab;
import ui.tabs.database.ListTab;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Location currLocation;


    public WhereToEatUI() {
        super("Where To Eat?");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());

        initializeDatabase();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.TOP);

        loadTabs();
        add(sidebar);
        setBackground(Color.GREEN);
        setVisible(true);
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

    // ADD ALL DATA RELEATED THINGS HERE
    private void initializeDatabase() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        database = new Database();
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
}
