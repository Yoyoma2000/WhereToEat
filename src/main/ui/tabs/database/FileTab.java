package ui.tabs.database;

import ui.Buttons;
import ui.WhereToEatUI;
import ui.tabs.Tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tab for saving and loading functions
public class FileTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "File Configs";
    private static final String SAVE_BUTTON = "Save database"; //unused
    private static final String LOAD_BUTTON = "Load database"; //unused
    private static final String SAVE_WARNING_CHECKBOX_CAPTION = "Notify to save before exit?"; //unused

    // Elements in UI
    private JLabel text;
    private JButton saveButton;
    private JButton loadButton;
    private JCheckBox saveWarningCheckBox;

    //private JScrollPane reportPane;
    //private JTextArea reportText;
    //private JSlider slider;
    //private JRadioButton checkButton;

    public FileTab(WhereToEatUI hub) {
        super(hub);
        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeFileButtons();
        placeSaveWarningCheckBox();
    }

    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 3);
        this.add(text);
    }

    private void placeFileButtons() {
        saveButton = new JButton(SAVE_BUTTON);
        loadButton = new JButton(LOAD_BUTTON);

        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(loadButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        saveButton.addActionListener(e -> {
            this.getHub().saveDataBase();
        });

        loadButton.addActionListener(e -> {
            this.getHub().loadDataBase();
        });

        this.add(buttonRow);
    }

    private void placeSaveWarningCheckBox() {
        saveWarningCheckBox = new JCheckBox(SAVE_WARNING_CHECKBOX_CAPTION);
        saveWarningCheckBox.setSize(WIDTH, HEIGHT / 3);
        this.add(saveWarningCheckBox);

    }

}
