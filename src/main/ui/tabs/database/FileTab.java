package ui.tabs.database;

import ui.WhereToEatUI;
import ui.tabs.Tab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

// Tab for saving and loading functions
public class FileTab extends Tab {
    // Texts used
    private static final String INIT_TITLE = "File Configs";
    //private static final String SAVE_BUTTON = "Save database"; //unused
    //private static final String LOAD_BUTTON = "Load database"; //unused
    private static final String SAVE_WARNING_CHECKBOX_CAPTION = "Notify to save before exit?";

    // Elements in UI
    private JLabel text;
    private JButton saveButton;
    private JButton loadButton;
    private JCheckBox saveWarningCheckBox;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds File tab to the main GUI, adds relevant elements to the tab
    public FileTab(WhereToEatUI hub) {
        super(hub);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(3, 1));

        placeTitle();
        try {
            placeFileButtons();
        } catch (IOException e) {
            //do nothing
        }
        //placeSaveWarningCheckBox();
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds title element to tab
    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 3);
        this.add(text);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds save/load buttons element for userinput to tab
    private void placeFileButtons() throws IOException {
        BufferedImage myPicture = ImageIO.read(new URL("https://w7.pngwing.com/pngs/563/899/png-transparent-computer-icons-favicon-save-icon-miscellaneous-text-rectangle.png"));
        Image scaledPicture = myPicture.getScaledInstance(70, 50, Image.SCALE_SMOOTH); //700,300
        Icon saveIcon = new ImageIcon(scaledPicture);
        saveButton = new JButton(saveIcon);

        myPicture = ImageIO.read(new URL("https://w7.pngwing.com/pngs/61/450/png-transparent-arrow-download-downloading-interface-save-essentials-pack-icon.png"));
        scaledPicture = myPicture.getScaledInstance(70, 50, Image.SCALE_SMOOTH); //700,300
        Icon loadIcon = new ImageIcon(scaledPicture);
        loadButton = new JButton(loadIcon);

        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(loadButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        saveButton.addActionListener(e -> {
            this.getHub().saveDataBase();
        });

        loadButton.addActionListener(e -> {
            this.getHub().loadDataBase();
        });
        buttonRow.setBackground(Color.WHITE);
        this.add(buttonRow);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds checkbox for savewarning element to tab
    private void placeSaveWarningCheckBox() {
        saveWarningCheckBox = new JCheckBox(SAVE_WARNING_CHECKBOX_CAPTION);
        saveWarningCheckBox.setSize(WIDTH, HEIGHT / 3);
        this.add(saveWarningCheckBox);
    }

}
