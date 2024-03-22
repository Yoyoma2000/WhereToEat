package ui.tabs.restaurant;

import model.Database;
import model.MenuItem;
import model.Restaurant;
import ui.Buttons;
import ui.WhereToEatUI;
import ui.tabs.Tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "Menu";
    private static final String LIST_COLUMN_NAMES = "Dish | Price";
    private static final String REFRESH_BUTTON = "Refresh";
    private JButton refreshButton;

    // Elements in UI
    private JLabel text;
    private JScrollPane listPane;

    //private JTextArea reportText;
    //private JTextField textField;
    //private JSlider slider;
    //private JCheckBox checkBox;

    // Tab Specific fields
    Restaurant selectedRes;
    private GridBagConstraints grid;

    public MenuTab(WhereToEatUI hub, Restaurant res) {
        super(hub);
        setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;
        this.selectedRes = res;

        placeTitle();
        placeRefreshButton();
        placeList();

    }

    private void placeRefreshButton() {
        refreshButton = new JButton(REFRESH_BUTTON);
        refreshButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 5;
        grid.gridy = 1;
        this.add(refreshButton, grid);

        refreshButton.addActionListener(e -> {
            this.remove(listPane);
            placeList();
        });
    }

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

    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 2;
        grid.gridy = 0;
        this.add(text, grid);
    }


}
