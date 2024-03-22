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
    private static final String INIT_TITLE = "Restaurant Information:";
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
    private GridBagConstraints grid;

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
        placeRefreshButton();
    }

    private void placeRefreshButton() {
        refreshButton = new JButton(REFRESH_BUTTON);
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

    private void placeTitle() {
        text = new JLabel(INIT_TITLE);
        text.setSize(WIDTH, HEIGHT / 3);
        grid.gridx = 1;
        grid.gridy = 0;
        this.add(text, grid);
    }

    private void placeText() {
        double distance = selectedRes.getDistance(getHub().getCurrLocation());
        String city = selectedRes.getLocation().getCityName();

        //text.setSize(WIDTH, HEIGHT / 3);
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

    private void placeAvgPrice() {
        double avgPrice = selectedRes.getAvgPrice();
        avgPriceText = new JLabel("\n Average Price: " + avgPrice);
        grid.gridx = 1;
        grid.gridy = 6;
        this.add(avgPriceText, grid);
    }

    private void placeFav() {
        favText = new JLabel("\n Favourite: " + selectedRes.isFavourite());
        grid.gridx = 1;
        grid.gridy = 7;
        this.add(favText, grid);
    }


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
