package ui.tabs;


import ui.Buttons;
import ui.WhereToEatUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Home tab with title and search function
public class HomeTab extends Tab {
    private static final String INIT_TITLE = "Where To Eat?";
    private JLabel text;

    public HomeTab(WhereToEatUI hub) {
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
