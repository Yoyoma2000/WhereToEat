package ui.tabs;

import javax.swing.*;
import ui.*;

import java.awt.*;

public abstract class Tab extends JPanel {
    private final WhereToEatUI mainHub;

    public Tab(WhereToEatUI mainHub) {
        this.mainHub = mainHub;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    public WhereToEatUI getHub() {
        return mainHub;
    }
}
