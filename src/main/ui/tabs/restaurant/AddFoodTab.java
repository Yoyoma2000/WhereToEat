package ui.tabs.restaurant;

import ui.WhereToEatUI;

import ui.tabs.Tab;

import javax.swing.*;
import java.awt.*;


public class AddFoodTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Add Dish";
    private static final String ADD_FOOD_BUTTON = "Add"; //unused
    private static final String DISH_NAME_FIELD = "Dish Name"; //unused
    private static final String DISH_PRICE_FIELD = "Price"; //unused

    // Elements in UI
    private JLabel text;
    private JButton addButton;
    private JTextField dishNameText;
    private JTextField dishPriceText;

    public AddFoodTab(WhereToEatUI hub) {
        super(hub);
        setLayout(new GridLayout(6, 1));

        placeTitle();

        placeDishFields();
        placeAddButton();
    }

    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 6);
        this.add(text);
    }

    private void placeAddButton() {
        addButton = new JButton(ADD_FOOD_BUTTON);
        addButton.setSize(WIDTH, HEIGHT / 6);
        this.add(addButton);
    }

    private void placeDishFields() {
        dishNameText = new JTextField(DISH_NAME_FIELD);
        dishNameText.setSize(WIDTH, HEIGHT / 6);
        this.add(dishNameText);

        dishPriceText = new JTextField(DISH_PRICE_FIELD);
        dishPriceText.setSize(WIDTH, HEIGHT / 6);
        this.add(dishPriceText);
    }

}
