package ui;

import model.Restaurant;
import model.Database;
import model.Location;
import model.MenuItem;

import java.util.ArrayList;
import java.util.Scanner;

public class WhereToEat {
    private Scanner input;
    private Database database;
    private Location currLocation;

    // EFFECTS: runs the database application
    public WhereToEat() {
        launchApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void launchApp() {
        boolean continueApp = true;
        String userInput = null;

        setup();

        while (continueApp) {
            mainMenu();
            userInput = input.next();
            userInput = userInput.toLowerCase();

            if (userInput.equals("quit")) {
                continueApp = false;
            } else {
                runInput(userInput);
            }
        }
        System.out.println("\n Bon Appetit.");
    }

    // MODIFIES: this
    // EFFECTS: sets up the database and inputs
    private void setup() {
        input = new Scanner(System.in);
        System.out.println("\n Please enter city");
        String currCity = input.next();
        System.out.println("\n Please set x coordinate: ");
        double coordX = Double.parseDouble(input.next());
        System.out.println("\n Please set y coordinate: ");
        double coordY = Double.parseDouble(input.next());
        currLocation = new Location(currCity, coordX, coordY);
        database = new Database();
    }

    // EFFECTS: shows console home screen with welcome message and commands
    private void mainMenu() {
        System.out.println("\n Welcome to the WhereToEat!");
        System.out.println("\n An infohub of " + database.getDataBase().size() + " restuarants.");
        System.out.println("\n Commands:");
        System.out.println("\t search - find a restaurant by name and city");
        System.out.println("\t list - list restaurants");
        System.out.println("\t info - learn about a restaurant");
        System.out.println("\t add - add new restaurant");
        System.out.println("\t quit - Exits Program");
    }

    // MODIFIES: this
    // EFFECTS: chooses method based on user input
    private void runInput(String userInput) {
        if (userInput.equals("search")) {
            doSearch();
        } else if (userInput.equals("list")) {
            doList();
        } else if (userInput.equals("info")) {
            doInfo(-1);
        } else if (userInput.equals("add")) {
            doAdd();
        } else {
            System.out.println("No function is associated with this input.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Finds restaurant in list
    private void doSearch() {
        String inputName = null;
        String inputCity = null;
        System.out.println("\n Please enter restaurant name: ");
        inputName = input.next();
        inputName = inputName.toLowerCase();

        System.out.println("\n Please enter city name: ");
        inputCity = input.next();
        inputCity = inputCity.toLowerCase();

        int pos = database.searchDatabase(inputName, inputCity);
        if (pos > -1) {
            System.out.println("\n We found the restaraunt, want its info? y/n:");
            if (input.next().equals("y")) {
                doInfo(pos);
            }
        } else {
            System.out.println("\n Sorry, we could not find your restaurant!");
        }
    }

    // MODIFIES: this
    // EFFECTS: lists out all restaurants
    private void doList() {
        System.out.println("\n Name | Genre | Rating | City | Distance | Index");
        for (Restaurant r: database.getProcessedDataBase()) {
            double distance = r.getDistance(currLocation);
            int indexPos = database.getProcessedDataBase().indexOf(r);
            String city = r.getLocation().getCityName();
            System.out.println("\n" + r.getName() + " | " + r.getGenre() + " | " + r.getRating() + " | "
                    + city + " | " + distance + " | " + indexPos);
        }
    }

    // MODIFIES: this
    // EFFECTS: lists out info about a restaurant
    private void doInfo(int pos) {
        while (pos < 0 || pos >= database.getProcessedDataBase().size()) {
            System.out.println("\n Please enter index of restaurant:");
            pos = input.nextInt();
            if (pos > database.getProcessedDataBase().size()) {
                System.out.println("\n Index is out of bounds.");
            }
        }
        Restaurant r = database.getProcessedDataBase().get(pos);
        double distance = r.getDistance(currLocation);
        String city = r.getLocation().getCityName();
        double avgPrice = r.getAvgPrice();
        System.out.println("\n Name: " + r.getName() + "\n Genre: " + r.getGenre() + "\n Rating: " + r.getRating()
                + "\n City: " +  city + "\n Distance:" + distance + "\n Average Price: " + avgPrice);

        System.out.println("\n See menu? y/n");
        if (input.next().equals("y")) {
            for (MenuItem food: r.getMenu()) {
                System.out.println(food.toString());
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: Adds new restaurant to database
    private void doAdd() {
        System.out.println("Enter Restaurant Name: ");
        String name = input.next();
        System.out.println("Enter Genre Name: ");
        String genre = input.next();
        System.out.println("Enter Rating (#/10): ");
        int rating = Integer.parseInt(input.next());
        System.out.println("Enter City Name: ");
        String cityName = input.next();
        System.out.println("Enter X coordinate: ");
        double coordX = Double.parseDouble(input.next());
        System.out.println("Enter Y coordinate: ");
        double coordY = Double.parseDouble(input.next());

        Location location = new Location(cityName, coordX, coordY);
        Restaurant restaurant = new Restaurant(name, genre, rating, location);
        database.addRestaurant(restaurant);
        database.resetProcessedDataBase();
        System.out.println("Added new restaurant!");
    }


}
