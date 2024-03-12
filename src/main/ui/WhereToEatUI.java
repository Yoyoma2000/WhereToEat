package ui;

import javax.swing.*;
import model.*;

public class WhereToEatUI extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;

    Database database;

    public WhereToEatUI() {
        super("Where To Eat?");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        database = new Database();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        add(sidebar);

        setVisible(true);
    }
}
