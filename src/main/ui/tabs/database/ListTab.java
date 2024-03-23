package ui.tabs.database;

import model.Database;
import model.Restaurant;
import ui.WhereToEatUI;
import ui.tabs.Tab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

// Tab for listing restaurants in database
public class ListTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Restaurants";
    private static final String LIST_COLUMN_NAMES = "Index | Name | Genre | Rating | City | Distance | Average Price";
    private static final String RESET_LIST_BUTTON = "Reset";
    private static final String SORT_LIST_BUTTON = "Sort";
    private static final String SORT_DROPDOWN = " Sort By:";
    private static final String ORDER_DROPDOWN = " Order:";
    private static final String SORT_ASCENDING_RADIOBUTTON = "Ascending";
    private static final String SORT_DESCENDING_RADIOBUTTON = "Descending";
    private static final String SORT_NEITHER_RADIOBUTTON = "...";
    private static final String SORT_NAME_RADIOBUTTON  = "Name";
    private static final String SORT_PRICE_RADIOBUTTON  = "Price";
    private static final String SORT_RATING_RADIOBUTTON  = "Rating";
    private static final String SORT_NOTHING_RADIOBUTTON  = "...";

    // Elements in UI
    private JLabel text;
    private JScrollPane listPane;
    private JButton resetButton;
    private JButton sortButton;
    private JComboBox<String> sortComboBox;
    private JComboBox<String> orderComboBox;

    // Tab specific fields:
    private DecimalFormat numberFormat = new DecimalFormat("#.00");
    private GridBagConstraints grid;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds tab for listing restaurants and necessary elements to main gui
    public ListTab(WhereToEatUI hub) {
        super(hub);
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;

        placeTitle();
        placeSortOptions();
        placeOrderOptions();
        placeSortButton();
        try {
            placeResetButton();
        } catch (IOException e) {
            //do nothing
        }
        placeList();
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds list element for user input to tab, displays all restaurants
    private void placeList() {
        DefaultListModel<String> stringList = new DefaultListModel<String>();
        stringList.addElement(LIST_COLUMN_NAMES);

        Database databaseRef = getHub().getDatabase();
        for (Restaurant r: databaseRef.getProcessedDataBase()) {
            double distance = r.getDistance(getHub().getCurrLocation());
            int indexPos = databaseRef.getProcessedDataBase().indexOf(r);
            double avgPrice = r.getAvgPrice();
            String city = r.getLocation().getCityName();

            stringList.addElement(indexPos + " | " + r.getName() + " | " + r.getGenre() + " | "
                    + r.getRating() + " | " + city + " | " + numberFormat.format(distance) + "km | $" + avgPrice);
        }

        JList resList = new JList(stringList);
        listPane = new JScrollPane(resList);
        listPane.setSize(WIDTH, HEIGHT);
        grid.gridx = 1;
        grid.gridy = 1;
        grid.gridwidth = 3;
        grid.gridheight = 5;
        this.add(listPane, grid);
        grid.gridheight = 1;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds title element to tab
    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 0;
        grid.gridwidth = 1;
        this.add(text, grid);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds sort preference comboboxes element for user input to tab
    private void placeSortOptions() {
        JLabel sortText = new JLabel(SORT_DROPDOWN);
        grid.gridx = 4;
        grid.gridy = 1;
        grid.gridwidth = 1;
        this.add(sortText, grid);
        sortComboBox = new JComboBox<String>();
        sortComboBox.addItem(SORT_NOTHING_RADIOBUTTON);
        sortComboBox.addItem(SORT_NAME_RADIOBUTTON);
        sortComboBox.addItem(SORT_PRICE_RADIOBUTTON);
        sortComboBox.addItem(SORT_RATING_RADIOBUTTON);
        grid.gridx = 5;
        grid.gridy = 1;
        grid.gridwidth = 1;
        this.add(sortComboBox, grid);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds order preference comboboxes element for user input to tab
    private void placeOrderOptions() {
        JLabel orderText = new JLabel(ORDER_DROPDOWN);
        grid.gridx = 4;
        grid.gridy = 3;
        grid.gridwidth = 1;
        this.add(orderText, grid);
        orderComboBox = new JComboBox<String>();
        orderComboBox.addItem(SORT_NEITHER_RADIOBUTTON);
        orderComboBox.addItem(SORT_ASCENDING_RADIOBUTTON);
        orderComboBox.addItem(SORT_DESCENDING_RADIOBUTTON);
        grid.gridx = 5;
        grid.gridy = 3;
        grid.gridwidth = 1;
        this.add(orderComboBox, grid);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds sort button element for user input to tab
    private void placeSortButton() {
        sortButton = new JButton(SORT_LIST_BUTTON);
        sortButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 4;
        grid.gridy = 5;
        grid.gridwidth = 1;
        this.add(sortButton, grid);

        sortButton.addActionListener(e -> {
            applySort();
            this.remove(listPane);
            placeList();
        });
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds reset button element for user input to tab
    private void placeResetButton() throws IOException {
        BufferedImage myPicture = ImageIO.read(new URL("https://png2.cleanpng.com/sh/b10627083b0f8b9c64240aeadefd19a9/L0KzQYq4UcAzN6h5fZH9cnHxg8HokvVvfF53fdh7ZYPrPbrqjB4uPZVnfagBNHG8QIXpgscvPWg9UagAOEa0RYi5V8U6OWI5Tas6LoDxd1==/transparent-refresh-icon-5dbe664a904bb7.578965861572759114591.png"));
        Image scaledPicture = myPicture.getScaledInstance(20, 20, Image.SCALE_SMOOTH); //700,300
        Icon resetIcon = new ImageIcon(scaledPicture);
        resetButton = new JButton(resetIcon); // used to be RESET_BUTTON_TEXT
        resetButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 5;
        grid.gridy = 5;
        grid.gridwidth = 1;
        this.add(resetButton, grid);

        resetButton.addActionListener(e -> {
            getHub().getDatabase().resetProcessedDataBase();
            this.remove(listPane);
            placeList();
        });
    }

    //REQUIRES: nothing
    //MODIFIES: this, database
    //EFFECTS: uses database sort functions, and applies sorting based on combobox inputs
    private void applySort() {
        String sortType = sortComboBox.getSelectedItem().toString().toLowerCase();
        String orderType = orderComboBox.getSelectedItem().toString();

        if (!sortType.equals("...")) {
            if (orderType.equals("Ascending")) {
                getHub().getDatabase().sortDatabaseAscending(sortType);
            } else if (orderType.equals("Descending")) {
                getHub().getDatabase().sortDatabaseDescending(sortType);
            } else {
                // Do nothing
            }
        }
    }

}
