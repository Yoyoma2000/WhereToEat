package ui.tabs.restaurant;

import model.MenuItem;
import model.Restaurant;
import ui.WhereToEatUI;
import ui.tabs.Tab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

// The menu tab with list of foods sold from restaurant in restaruant-info window
public class MenuTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Menu";
    private static final String LIST_COLUMN_NAMES = "Dish | Price";
    //private static final String REFRESH_BUTTON = "Refresh";

    // Elements in UI
    private JLabel text;
    private JScrollPane listPane;
    private JButton refreshButton;

    // Tab Specific fields
    Restaurant selectedRes;
    private GridBagConstraints grid;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds tab for listing menu items and relevant elements for the restaurant info window
    public MenuTab(WhereToEatUI hub, Restaurant res) {
        super(hub);
        setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;
        this.selectedRes = res;

        placeTitle();
        try {
            placeRefreshButton();
        } catch (IOException e) {
            // Do nothing
        }
        placeList();

    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds refresh button element for user input to tab
    private void placeRefreshButton() throws IOException {
        BufferedImage myPicture = ImageIO.read(new URL("https://png2.cleanpng.com/sh/b10627083b0f8b9c64240aeadefd19a9/L0KzQYq4UcAzN6h5fZH9cnHxg8HokvVvfF53fdh7ZYPrPbrqjB4uPZVnfagBNHG8QIXpgscvPWg9UagAOEa0RYi5V8U6OWI5Tas6LoDxd1==/transparent-refresh-icon-5dbe664a904bb7.578965861572759114591.png"));
        Image scaledPicture = myPicture.getScaledInstance(20, 20, Image.SCALE_SMOOTH); //700,300
        Icon resetIcon = new ImageIcon(scaledPicture);
        refreshButton = new JButton(resetIcon);
        refreshButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 5;
        grid.gridy = 1;
        this.add(refreshButton, grid);

        refreshButton.addActionListener(e -> {
            this.remove(listPane);
            placeList();
        });
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds list of menu items element to tab
    public void placeList() {
        DefaultListModel<String> stringList = new DefaultListModel<String>();
        stringList.addElement(LIST_COLUMN_NAMES);

        for (MenuItem food: selectedRes.getMenu()) {
            stringList.addElement(food.toString());
        }

        JList resList = new JList(stringList);

        listPane = new JScrollPane(resList);
        listPane.setSize(WIDTH, HEIGHT / 2);
        grid.gridx = 2;
        grid.gridy = 1;
        grid.gridwidth = 3;
        grid.gridheight = 5;
        this.add(listPane, grid);
        grid.gridwidth = 1;
        grid.gridheight = 1;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds title element to tab
    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 2;
        grid.gridy = 0;
        this.add(text, grid);
    }


}
