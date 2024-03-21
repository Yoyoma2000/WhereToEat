package ui.tabs.restaurant;

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

    // Elements in UI
    private JLabel text;
    private JScrollPane listPane;

    //private JTextArea reportText;
    //private JTextField textField;
    //private JSlider slider;
    //private JCheckBox checkBox;

    public MenuTab(WhereToEatUI hub) {
        super(hub);
        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeList();

    }

    private void placeList() {
        JLabel topText = new JLabel(LIST_COLUMN_NAMES);
        listPane = new JScrollPane(topText);
        listPane.setSize(WIDTH, HEIGHT / 2);
        this.add(listPane);
    }

    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 6);
        this.add(text);
    }


}
