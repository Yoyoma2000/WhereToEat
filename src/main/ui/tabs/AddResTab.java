package ui.tabs;

import ui.WhereToEatUI;
import ui.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Tab for adding new restaurants
public class AddResTab extends Tab {
    private static final String INIT_TITLE = "Add Restaurant";
    private JLabel text;


    public AddResTab(WhereToEatUI hub) {
        super(hub);

        setLayout(new GridLayout(3, 1));
        title();


    }

    private void title() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 3);
        this.add(text);
    }

}
