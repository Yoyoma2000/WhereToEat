package ui.tabs.restaurant;

import model.Restaurant;
import model.MenuItem;
import ui.WhereToEatUI;

import ui.tabs.Tab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

// The tab to add new dish to restaraunt in restaruant-info window.
public class AddFoodTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Add Dish";
    //private static final String ADD_FOOD_BUTTON = "Add"; //unused
    private static final String DISH_NAME_FIELD = "Dish Name";
    private static final String DISH_PRICE_FIELD = "Price";

    // Elements in UI
    private JLabel text;
    private JButton addButton;
    private JTextField dishNameText;
    private JTextField dishPriceText;

    // Tab Specific fields
    private Restaurant selectedRes;
    private GridBagConstraints grid;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds add food tab for adding new dishes to restaraunt in restaurant info window
    public AddFoodTab(WhereToEatUI hub, Restaurant res) {
        super(hub);
        setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;

        this.selectedRes = res;

        placeTitle();
        placeDishFields();
        try {
            placeAddButton();
        } catch (IOException e) {
            //Do Nothing
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds title element to tab
    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 0;
        grid.gridwidth = 2;
        this.add(text, grid);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds add-dish button element for user input tab
    private void placeAddButton() throws IOException {
        BufferedImage myPicture = ImageIO.read(new URL("https://cdn4.iconfinder.com/data/icons/food-delivery-90/512/Food_order_eat-04-512.png"));
        Image scaledPicture = myPicture.getScaledInstance(70, 70, Image.SCALE_SMOOTH); //700,300
        Icon addIcon = new ImageIcon(scaledPicture);
        addButton = new JButton(addIcon);
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

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds text-fields relevant to MenuItem for user input to tab
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
