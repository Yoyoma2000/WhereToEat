package ui.tabs.database;

import model.Location;
import model.Restaurant;
import ui.WhereToEatUI;
import ui.tabs.Tab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


// Tab for adding new restaurants
public class AddResTab extends Tab {

    // Texts used
    private static final String INIT_TITLE = "Add Restaurant";
    //private static final String ADD_RES_BUTTON = "Add"; //unused
    private static final String RES_NAME_FIELD = "Restaurant Name";
    private static final String RES_GENRE_FIELD = "Genre";
    private static final String RES_RATING_FIELD = "Rating (1-10)";
    private static final String RES_CITY_FIELD = "City Name";
    private static final String RES_X_FIELD = "X Coordinate";
    private static final String RES_Y_FIELD = "Y Coordinate";

    // Elements in UI
    private JLabel text;
    private JButton addButton;
    private JTextField resNameText;
    private JTextField resGenreText;
    private JTextField resRatingText;
    private JTextField resCityText;
    private JTextField resXText;
    private JTextField resYText;

    // Tab specific:
    private GridBagConstraints grid;

    public AddResTab(WhereToEatUI hub) {
        super(hub);
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;

        placeTitle();

        placeRestaruantFieldsRow1();
        placeRestaruantFieldsRow2();
        try {
            placeAddButton();
        } catch (IOException e) {
            //donothing
        }
    }

    private void placeTitle() {
        text = new JLabel(INIT_TITLE, JLabel.CENTER);
        text.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 3;
        grid.gridy = 0;
        grid.gridwidth = 2;
        this.add(text, grid);
    }

    private void placeAddButton() throws IOException {
        BufferedImage myPicture = ImageIO.read(new URL("https://cdn-icons-png.flaticon.com/512/3325/3325784.png"));
        Image scaledPicture = myPicture.getScaledInstance(50, 50, Image.SCALE_SMOOTH); //700,300
        Icon addIcon = new ImageIcon(scaledPicture);
        addButton = new JButton(addIcon);
        addButton.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 4;
        grid.gridy = 3;
        grid.gridwidth = 2;
        this.add(addButton, grid);

        addButton.addActionListener(e -> {
            String resName = resNameText.getText();
            String resGenre = resGenreText.getText();
            int resRating = Integer.parseInt(resRatingText.getText());
            String cityName = resCityText.getText();
            Double coordX = Double.parseDouble(resXText.getText());
            Double coordY = Double.parseDouble(resYText.getText());

            Location resLocation = new Location(cityName, coordX, coordY);

            Restaurant newRestaurant = new Restaurant(resName, resGenre, resRating, resLocation);
            this.getHub().getDatabase().addRestaurant(newRestaurant);
        });
    }

    private void placeRestaruantFieldsRow1() {
        resNameText = new JTextField(RES_NAME_FIELD);
        resNameText.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 1;
        grid.gridwidth = 2;
        this.add(resNameText, grid);

        resGenreText = new JTextField(RES_GENRE_FIELD);
        resGenreText.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 3;
        grid.gridy = 1;
        grid.gridwidth = 2;
        this.add(resGenreText, grid);

        resRatingText = new JTextField(RES_RATING_FIELD);
        resRatingText.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 5;
        grid.gridy = 1;
        grid.gridwidth = 2;
        this.add(resRatingText, grid);
    }

    private void placeRestaruantFieldsRow2() {

        resCityText = new JTextField(RES_CITY_FIELD);
        resCityText.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 1;
        grid.gridy = 2;
        grid.gridwidth = 2;
        this.add(resCityText, grid);

        resXText = new JTextField(RES_X_FIELD);
        resXText.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 3;
        grid.gridy = 2;
        grid.gridwidth = 2;
        this.add(resXText, grid);

        resYText = new JTextField(RES_Y_FIELD);
        resYText.setSize(WIDTH, HEIGHT / 6);
        grid.gridx = 5;
        grid.gridy = 2;
        grid.gridwidth = 2;
        this.add(resYText, grid);
    }

}
