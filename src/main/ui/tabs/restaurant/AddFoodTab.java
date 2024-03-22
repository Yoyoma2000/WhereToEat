package ui.tabs.restaurant;

import model.Restaurant;
import model.MenuItem;
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

    // Tab Specific fields
    Restaurant selectedRes;

    private GridBagConstraints grid;

    public AddFoodTab(WhereToEatUI hub, Restaurant res) {
        super(hub);
        setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;

        this.selectedRes = res;

        placeTitle();
        placeDishFields();
        placeAddButton();
    }

    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 0;
        grid.gridwidth = 2;
        this.add(text, grid);
    }

    private void placeAddButton() {
        addButton = new JButton(ADD_FOOD_BUTTON);
        addButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 4;
        grid.gridwidth = 2;
        this.add(addButton, grid);

        addButton.addActionListener(e -> {
            String name = dishNameText.getText();
            double price = Double.parseDouble(dishPriceText.getText());

            MenuItem newDish = new MenuItem(name, price);
            selectedRes.addToMenu(newDish);
        });
    }

    private void placeDishFields() {
        dishNameText = new JTextField(DISH_NAME_FIELD);
        dishNameText.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 2;
        grid.gridwidth = 2;
        this.add(dishNameText, grid);

        dishPriceText = new JTextField(DISH_PRICE_FIELD);
        dishPriceText.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 3;
        grid.gridwidth = 2;
        this.add(dishPriceText, grid);
    }

}
