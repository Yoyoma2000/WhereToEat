package ui.tabs.database;


import model.Location;
import model.Restaurant;
import ui.WhereToEatUI;
import ui.tabs.Tab;
import ui.tabs.restaurant.AddFoodTab;
import ui.tabs.restaurant.InfoTab;
import ui.tabs.restaurant.MenuTab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

// Home tab with title and search function
public class HomeTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Where To Eat?";
    private static final String SEARCH_BAR1 = "Enter index number"; //unused
    private static final String SEARCH_BAR2 = "Enter restaurant name-city"; //unused
    private static final String SEARCH = "Search"; //unused
    private static final String SEARCH_INDEX_RADIOBUTTON = "By Index"; //unused
    private static final String SEARCH_NAME_RADIOBUTTON = "By Name"; //unused
    private static final String RANDOM_BUTTON = "Random"; //unused

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
    private GridBagConstraints grid;
    boolean indexSearchMode = true;

    private BufferedImage background;




    public HomeTab(WhereToEatUI hub) {
        super(hub);
        setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        //grid.anchor = GridBagConstraints.CENTER;
        grid.fill = GridBagConstraints.HORIZONTAL;
        //grid.weightx = 1;
        //grid.weighty = 1;

        placeTitle();
        placeSearchBar();
        placeButtons();
        placeSearchCheckButtons();

        placeCurrLocationButton();
        placeCurrLocationFields();
        try {
            setBackgroundImage();
        } catch (IOException e) {
            // Do nothing
        }
    }


    private void setBackgroundImage() throws IOException {
        BufferedImage myPicture = ImageIO.read(new URL("https://www.mqtuu.org/wp-content/uploads/2018/10/cornucopia-banner-e1353008079917.jpg"));
        Image scaledPicture = myPicture.getScaledInstance(500, 160, Image.SCALE_SMOOTH); //700,300
        JLabel picLabel = new JLabel(new ImageIcon(scaledPicture));
        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 10;
        grid.gridheight = 10;
        add(picLabel, grid);
        grid.gridwidth = 1;
        grid.gridheight = 1;

    }

    private void placeTitle() {
        title = new JLabel(INIT_TITLE, JLabel.CENTER);
        title.setSize(WIDTH, HEIGHT / 6);

        grid.gridx = 1;
        grid.gridy = 0;
        grid.gridwidth = 1;

        this.add(title, grid);
    }

    private void placeCurrLocationFields() {
        cityField = new JTextField(CURR_CITY);
        cityField.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 3;
        grid.gridwidth = 2;
        this.add(cityField, grid);
        currXField = new JTextField(CURR_X);
        currXField.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 3;
        grid.gridy = 3;
        grid.gridwidth = 1;
        this.add(currXField, grid);
        currYField = new JTextField(CURR_Y);
        currYField.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 4;
        grid.gridy = 3;
        grid.gridwidth = 1;
        this.add(currYField, grid);
    }

    private void placeCurrLocationButton() {
        currButton = new JButton(CURR_BUTTON);
        currButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 5;
        grid.gridy = 3;
        grid.gridwidth = 1;
        this.add(currButton, grid);
        currButton.addActionListener(e -> {
            String cityName = cityField.getText();
            Double currX = Double.parseDouble(currXField.getText());
            Double currY = Double.parseDouble(currYField.getText());
            Location currLocation = new Location(cityName, currX, currY);
            this.getHub().setCurrLocation(currLocation);
        });
    }

    private void placeSearchBar() {
        searchBarField = new JTextField(SEARCH_BAR1);
        searchBarField.setSize(WIDTH * 2, HEIGHT / 6);

        grid.gridx = 1;
        grid.gridy = 1;
        grid.gridwidth = 3;
        this.add(searchBarField, grid);
    }

    private void placeButtons() {
        searchButton = new JButton(SEARCH);
        searchButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 4;
        grid.gridy = 1;
        grid.gridwidth = 1;
        this.add(searchButton, grid);
        randomButton = new JButton(RANDOM_BUTTON);
        randomButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 5;
        grid.gridy = 1;
        grid.gridwidth = 1;
        this.add(randomButton, grid);

        searchButton.addActionListener(e -> {
            doSearch();
        });

        randomButton.addActionListener(e -> {
            Random rand = new Random();
            int randomIndex = rand.nextInt(getHub().getDatabase().getProcessedDataBase().size());
            searchRestaurant(randomIndex);
        });
    }

    private void doSearch() {
        int index;
        if (!indexSearchMode) {
            String searchInput = searchBarField.getText().toLowerCase();
            String[] enteredValues = searchInput.split("-");
            index = getHub().getDatabase().searchDatabase(enteredValues[0], enteredValues[1]);
        } else {
            index = Integer.parseInt(searchBarField.getText());
        }
        if (index == -1) {
            searchBarField.setText("No Restaurant Found!");
        } else {
            searchRestaurant(index);
        }
    }

    private void placeSearchCheckButtons() {
        searchByIndexButton = new JRadioButton(SEARCH_INDEX_RADIOBUTTON);
        searchByIndexButton.setSize(WIDTH, HEIGHT / 6);
        searchByIndexButton.setForeground(new Color(110, 227, 77, 255));
        grid.gridx = 2;
        grid.gridy = 2;
        grid.gridwidth = 1;
        this.add(searchByIndexButton, grid);
        searchByNameButton = new JRadioButton(SEARCH_NAME_RADIOBUTTON);
        searchByNameButton.setSize(WIDTH, HEIGHT / 6);
        searchByNameButton.setForeground(new Color(103, 215, 72, 255));
        grid.gridx = 3;
        grid.gridy = 2;
        this.add(searchByNameButton, grid);
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
    private void searchRestaurant(int index) {
        Restaurant selectedRes = getHub().getDatabase().getDataBase().get(index);

        restaurantInfo = new JFrame("Restaurant Info");
        restaurantInfo.setSize(600, 400);
        JTabbedPane sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.TOP);

        JPanel infoTab = new InfoTab(this.getHub(), selectedRes);
        JPanel menuTab = new MenuTab(this.getHub(), selectedRes);
        JPanel addFoodTab = new AddFoodTab(this.getHub(), selectedRes);

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
