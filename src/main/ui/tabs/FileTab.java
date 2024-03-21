package ui.tabs;

import ui.Buttons;
import ui.WhereToEatUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tab for saving and loading functions
public class FileTab extends Tab {
    private static final String INIT_TITLE = "File Configs";
    private JLabel text;


    public FileTab(WhereToEatUI hub) {
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
