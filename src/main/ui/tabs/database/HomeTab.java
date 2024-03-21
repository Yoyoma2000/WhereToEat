package ui.tabs.database;


import model.Location;
import ui.Buttons;
import ui.WhereToEatUI;
import ui.tabs.Tab;
import ui.tabs.restaurant.AddFoodTab;
import ui.tabs.restaurant.InfoTab;
import ui.tabs.restaurant.MenuTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

// Home tab with title and search function
public class HomeTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Where To Eat?";
    private static final String SEARCH_BAR1 = "Enter index number"; //unused
    private static final String SEARCH_BAR2 = "Enter restaurant name,city"; //unused
    private static final String SEARCH = "Search"; //unused
    private static final String SEARCH_INDEX_RADIOBUTTON = "By Index"; //unused
    private static final String SEARCH_NAME_RADIOBUTTON = "By Name"; //unused
    private static final String RANDOM_BUTTON = "I'm Feeling Lucky!"; //unused

    private static final String CURR_CITY = "Current City"; //unused
    private static final String CURR_X = "Current X"; //unused
    private static final String CURR_Y = "Current Y"; //unused
    private static final String CURR_BUTTON = "Submit"; //unused

    // Elements in UI
    private JLabel title;
    private JTextField searchBarField;
    private JButton searchButton;
    private JRadioButton searchByIndexButton;
    private JRadioButton searchByNameButton;
    private JButton randomButton;

    private JTextField cityField;
    private JTextField currXField;
    private JTextField currYField;
    private JButton currButton;

    private JFrame restaurantInfo;

    // Tab specific values:
    boolean indexSearchMode = true;

    public HomeTab(WhereToEatUI hub) {
        super(hub);
        setLayout(new GridLayout(6, 1));

        placeTitle();
        placeSearchBar();
        placeButtons();
        placeSearchCheckButtons();

        placeCurrLocation();
    }

    private void placeCurrLocation() {
        cityField = new JTextField(CURR_CITY);
        cityField.setSize(WIDTH, HEIGHT / 6);
        this.add(cityField);
        currXField = new JTextField(CURR_X);
        currXField.setSize(WIDTH, HEIGHT / 6);
        this.add(currXField);
        currYField = new JTextField(CURR_Y);
        currYField.setSize(WIDTH, HEIGHT / 6);
        this.add(currYField);

        currButton = new JButton(CURR_BUTTON);
        currButton.setSize(WIDTH, HEIGHT / 6);
        this.add(currButton);

        currButton.addActionListener(e -> {
            String cityName = cityField.getText();
            Double currX = Double.parseDouble(currXField.getText());
            Double currY = Double.parseDouble(currYField.getText());

            Location currLocation = new Location(cityName, currX, currY);
            this.getHub().setCurrLocation(currLocation);
        });
    }

    private void placeTitle() {
        title = new JLabel(INIT_TITLE, JLabel.CENTER);
        title.setSize(WIDTH, HEIGHT / 6);
        this.add(title);
    }

    private void placeSearchBar() {
        searchBarField = new JTextField(SEARCH_BAR1);
        searchBarField.setSize(WIDTH, HEIGHT / 6);
        this.add(searchBarField);
    }

    private void placeButtons() {
        searchButton = new JButton(SEARCH);
        searchButton.setSize(WIDTH, HEIGHT / 6);
        randomButton = new JButton(RANDOM_BUTTON);
        randomButton.setSize(WIDTH, HEIGHT / 6);

        JPanel buttonRow = formatButtonRow(searchButton);
        buttonRow.add(randomButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        searchButton.addActionListener(e -> {
            int index;
            if (!indexSearchMode) {
                index = getHub().getDatabase().searchDatabase("placeholder","placeholder");
            } else {
                index = Integer.parseInt(searchBarField.getText());
            }

            searchRestaurant();//Put in randomIndex
        });

        randomButton.addActionListener(e -> {
            Random rand = new Random();
            int randomIndex = rand.nextInt(getHub().getDatabase().getProcessedDataBase().size());
            searchRestaurant(); //Put in randomIndex
        });

        this.add(buttonRow);
    }

    private void placeSearchCheckButtons() {
        searchByIndexButton = new JRadioButton(SEARCH_INDEX_RADIOBUTTON);
        searchByIndexButton.setSize(WIDTH, HEIGHT / 6);
        this.add(searchByIndexButton);

        searchByNameButton = new JRadioButton(SEARCH_NAME_RADIOBUTTON);
        searchByNameButton.setSize(WIDTH, HEIGHT / 6);
        this.add(searchByNameButton);

        searchByIndexButton.addActionListener(e -> {
            searchByNameButton.setSelected(false);
            searchBarField.setText(SEARCH_BAR1);
            indexSearchMode = true;
        });

        searchByNameButton.addActionListener(e -> {
            searchByIndexButton.setSelected(false);
            searchBarField.setText(SEARCH_BAR2);
            indexSearchMode = false;
        });
    }

    //ADD (int index) argument later
    private void searchRestaurant() {
        restaurantInfo = new JFrame("Restaurant Info");
        restaurantInfo.setSize(600, 400);
        JTabbedPane sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.TOP);

        JPanel infoTab = new InfoTab(this.getHub());
        JPanel menuTab = new MenuTab(this.getHub());
        JPanel addFoodTab = new AddFoodTab(this.getHub());

        sidebar.add(infoTab, 0);
        sidebar.setTitleAt(0, "Info");
        sidebar.add(menuTab, 1);
        sidebar.setTitleAt(1, "Menu");
        sidebar.add(addFoodTab, 2);
        sidebar.setTitleAt(2, "Add");

        restaurantInfo.add(sidebar);

        restaurantInfo.setVisible(true);
        //this.add(restaurantInfo);  This code is actually not needed, new window is already created
    }


}
