package ui.tabs.restaurant;

import model.Restaurant;
import ui.WhereToEatUI;

import ui.tabs.Tab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

// The main info tab for restaraunt in restaruant-info window
public class InfoTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Restaurant Information:";
    private static final String FAV_CHECKBOX_CAPTION = "Favourite";
    //private static final String REFRESH_BUTTON = "Refresh";


    // Elements in UI
    private JLabel text;
    private JLabel favText;
    private JLabel avgPriceText;
    private JCheckBox favCheckBox;
    private JButton refreshButton;

    // Tab Specific fields
    private Restaurant selectedRes;
    private GridBagConstraints grid;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds info tab displaying restaurant info and relevant elements to restaurant info window
    public InfoTab(WhereToEatUI hub, Restaurant res) {
        super(hub);
        setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;

        this.selectedRes = res;

        placeTitle();
        placeText();
        placeAvgPrice();
        placeFav();
        placeFavCheckBox();
        try {
            placeRefreshButton();
        } catch (IOException e) {
            // Do nothing
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds refresh button element for user input to tab
    private void placeRefreshButton() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("./data/0.png"));
        Image scaledPicture = myPicture.getScaledInstance(20, 20, Image.SCALE_SMOOTH); //700,300
        Icon resetIcon = new ImageIcon(scaledPicture);
        refreshButton = new JButton(resetIcon);
        refreshButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 3;
        grid.gridy = 3;
        this.add(refreshButton, grid);

        refreshButton.addActionListener(e -> {
            this.remove(avgPriceText);
            placeAvgPrice();
            this.remove(favText);
            placeFav();
        });
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds title element to tab
    private void placeTitle() {
        text = new JLabel(INIT_TITLE);
        text.setSize(WIDTH, HEIGHT / 3);
        grid.gridx = 1;
        grid.gridy = 0;
        this.add(text, grid);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds text element for displaying restaurant info to tab
    private void placeText() {
        double distance = selectedRes.getDistance(getHub().getCurrLocation());
        String city = selectedRes.getLocation().getCityName();

        grid.gridx = 1;
        grid.gridy = 1;
        this.add(new JLabel("\n Name: " + selectedRes.getName()), grid);
        grid.gridx = 1;
        grid.gridy = 2;
        this.add(new Label("\n Genre: " + selectedRes.getGenre()), grid);
        grid.gridx = 1;
        grid.gridy = 3;
        this.add(new JLabel("\n Rating: " + selectedRes.getRating()), grid);
        grid.gridx = 1;
        grid.gridy = 4;
        this.add(new JLabel("\n City: " +  city), grid);
        grid.gridx = 1;
        grid.gridy = 5;
        this.add(new JLabel("\n Distance:" + distance), grid);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds text element for displaying restaurant avg price to tab
    private void placeAvgPrice() {
        double avgPrice = selectedRes.getAvgPrice();
        avgPriceText = new JLabel("\n Average Price: " + avgPrice);
        grid.gridx = 1;
        grid.gridy = 6;
        this.add(avgPriceText, grid);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds text element for displaying restaurant favourite preference to tab
    private void placeFav() {
        favText = new JLabel("\n Favourite: " + selectedRes.isFavourite());
        grid.gridx = 1;
        grid.gridy = 7;
        this.add(favText, grid);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds favourite checkbox element for userinput tab
    private void placeFavCheckBox() {
        favCheckBox = new JCheckBox(FAV_CHECKBOX_CAPTION);
        favCheckBox.setSize(WIDTH, HEIGHT / 3);
        grid.gridx = 3;
        grid.gridy = 1;
        this.add(favCheckBox, grid);

        favCheckBox.addActionListener(e -> {
            selectedRes.toggleFavourite();
        });
    }

}
