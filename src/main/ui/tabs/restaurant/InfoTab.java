package ui.tabs.restaurant;

import model.Restaurant;
import ui.WhereToEatUI;

import ui.Buttons;
import ui.WhereToEatUI;
import ui.tabs.Tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Restaurant Information";
    private static final String FAV_CHECKBOX_CAPTION = "Favourite"; //unused
    private static final String REFRESH_BUTTON = "Refresh";


    // Elements in UI
    private JLabel text;
    private JLabel favText;
    private JLabel avgPriceText;
    private JCheckBox favCheckBox;
    private JButton refreshButton;

    // Tab Specific fields
    Restaurant selectedRes;

    public InfoTab(WhereToEatUI hub, Restaurant res) {
        super(hub);
        setLayout(new GridLayout(3, 1));
        this.selectedRes = res;

        placeTitle();
        placeText();
        placeAvgPrice();
        placeFav();
        placeFavCheckBox();
        placeRefreshButton();
    }

    private void placeRefreshButton() {
        refreshButton = new JButton(REFRESH_BUTTON);
        refreshButton.setSize(WIDTH, HEIGHT / 6);
        this.add(refreshButton);

        refreshButton.addActionListener(e -> {
            this.remove(avgPriceText);
            placeAvgPrice();
            this.remove(favText);
            placeFav();
        });
    }

    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 3);
        this.add(text);
    }

    private void placeText() {
        double distance = selectedRes.getDistance(getHub().getCurrLocation());
        String city = selectedRes.getLocation().getCityName();


        //text.setSize(WIDTH, HEIGHT / 3);
        this.add(new JLabel("\n Name: " + selectedRes.getName(), JLabel.CENTER));
        this.add(new JLabel("\n Genre: " + selectedRes.getGenre(), JLabel.CENTER));
        this.add(new JLabel("\n Rating: " + selectedRes.getRating(), JLabel.CENTER));
        this.add(new JLabel("\n City: " +  city, JLabel.CENTER));
        this.add(new JLabel("\n Distance:" + distance, JLabel.CENTER));
    }

    private void placeAvgPrice() {
        double avgPrice = selectedRes.getAvgPrice();
        avgPriceText = new JLabel("\n Average Price: " + avgPrice, JLabel.CENTER);
        this.add(avgPriceText);
    }

    private void placeFav() {
        favText = new JLabel("\n Favourite: " + selectedRes.isFavourite(), JLabel.CENTER);
        this.add(favText);
    }


    private void placeFavCheckBox() {
        favCheckBox = new JCheckBox(FAV_CHECKBOX_CAPTION);
        favCheckBox.setSize(WIDTH, HEIGHT / 3);
        this.add(favCheckBox);

        favCheckBox.addActionListener(e -> {
            selectedRes.toggleFavourite();
        });
    }

}
