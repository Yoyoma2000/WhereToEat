package ui.tabs.restaurant;

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

    // Elements in UI
    private JLabel text;
    private JCheckBox favCheckBox;

    public InfoTab(WhereToEatUI hub) {
        super(hub);
        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeFavCheckBox();
    }

    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 3);
        this.add(text);
    }


    private void placeFavCheckBox() {
        favCheckBox = new JCheckBox(FAV_CHECKBOX_CAPTION);
        favCheckBox.setSize(WIDTH, HEIGHT / 3);
        this.add(favCheckBox);
    }

}
