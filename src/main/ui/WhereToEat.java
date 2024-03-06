package ui;

import model.Restaurant;
import model.Database;
import model.Location;
import model.MenuItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

// This class is the UI that controls the restaurant and database classes
public class WhereToEat {
    private Scanner input;
    private Database database;
    private Location currLocation;
    private DecimalFormat numberFormat;

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
            input.reset();
            mainMenu();
            userInput = input.nextLine();
            input.reset();
            userInput = userInput.toLowerCase();

            if (userInput.equals("quit")) {
                continueApp = false;
            } else {
                runInput(userInput);
            }
        }
        System.out.println("\n Bon Appetit!");
    }

    // MODIFIES: this
    // EFFECTS: sets up the database and inputs
    private void setup() {
        input = new Scanner(System.in);
        System.out.println("\n Please enter current city");
        String currCity = input.nextLine();
        input.reset();
        System.out.println("\n Please set x coordinate: ");
        double coordX = Double.parseDouble(input.nextLine());
        input.reset();
        System.out.println("\n Please set y coordinate: ");
        double coordY = Double.parseDouble(input.nextLine());
        input.reset();
        currLocation = new Location(currCity, coordX, coordY);
        database = new Database();
        numberFormat = new DecimalFormat("#.00");

        //Adding a few example restaurants
        loadExampleRestaurants();
    }

    // Examples for the sake of presentation, will turn into load data function
    // MODIFIES: this
    // EFFECTS: adds restaurants into the database
    private void loadExampleRestaurants() {
        Location l0 = new Location("Vancouver", 76, 85);
        Restaurant r0 = new Restaurant("Chipotle", "Mexican", 6, l0);
        Location l1 = new Location("Metrotown", 20, 85);
        Restaurant r1 = new Restaurant("Lamb Hotpot", "Chinese", 8, l1);
        Location l2 = new Location("Surrey", 50, 25);
        Restaurant r2 = new Restaurant("Pho86", "Vietnamese", 10, l2);
        Location l3 = new Location("Burnaby", 25, 25);
        Restaurant r3 = new Restaurant("Lulu Kitchen", "Chinese", 9, l3);
        database.addRestaurant(r0);
        database.addRestaurant(r1);
        database.addRestaurant(r2);
        database.addRestaurant(r3);
        database.resetProcessedDataBase();
        loadExampleMenuItems();
    }

    // EFFECTS: adds menu items into database
    private void loadExampleMenuItems() {
        MenuItem chipotle1 = new MenuItem("Bowl", 15);
        MenuItem chipotle2 = new MenuItem("Taco", 10);
        MenuItem pot1 = new MenuItem("tomato", 30);
        MenuItem pot2 = new MenuItem("yinyang", 25);
        MenuItem pho1 = new MenuItem("House Special", 12);
        MenuItem pho2 = new MenuItem("Spicy", 18);
        MenuItem lulu1 = new MenuItem("Beef shank", 6.5);
        MenuItem lulu2 = new MenuItem("Lamb", 8.5);
        database.getDataBase().get(0).addToMenu(chipotle1);
        database.getDataBase().get(0).addToMenu(chipotle2);
        database.getDataBase().get(1).addToMenu(pot1);
        database.getDataBase().get(1).addToMenu(pot2);
        database.getDataBase().get(2).addToMenu(pho1);
        database.getDataBase().get(2).addToMenu(pho2);
        database.getDataBase().get(3).addToMenu(lulu1);
        database.getDataBase().get(3).addToMenu(lulu2);
    }

    // EFFECTS: shows console home screen with welcome message and commands
    private void mainMenu() {
        System.out.println("\n<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>");
        System.out.println("Welcome to the WhereToEat!");
        System.out.println("An infohub of " + database.getDataBase().size() + " restuarants.");
        System.out.println("<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>");
        System.out.println("Commands:");
        System.out.println("\t search - find a restaurant by name and city");
        System.out.println("\t list - list restaurants");
        System.out.println("\t random - arbitrarily choose a restaurant for you");
        System.out.println("\t sort - sorts the database by a restaurant field");
        System.out.println("\t info - learn about a restaurant");
        System.out.println("\t add - add new restaurant");
        System.out.println("\t quit - Exits Program");
        System.out.println("<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>+<>");
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
        } else if (userInput.equals("random")) {
            doRandom();
        } else if (userInput.equals("sort")) {
            doSort();
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
        inputName = input.nextLine();
        inputName = inputName.toLowerCase();

        System.out.println("\n Please enter city name: ");
        inputCity = input.nextLine();
        inputCity = inputCity.toLowerCase();

        int pos = database.searchDatabase(inputName, inputCity);
        if (pos > -1) {
            System.out.println("\n We found the restaraunt, want its info? y/n:");
            if (input.nextLine().equals("y")) {
                doInfo(pos);
            }
        } else {
            System.out.println("\n Sorry, we could not find your restaurant!");
        }
    }

    // MODIFIES: this
    // EFFECTS: lists out all restaurants
    private void doList() {
        System.out.println("\n List size: " + database.getProcessedDataBase().size());
        System.out.println("\n Index | Name | Genre | Rating | City | Distance | Average Price");
        for (Restaurant r: database.getProcessedDataBase()) {
            double distance = r.getDistance(currLocation);
            int indexPos = database.getProcessedDataBase().indexOf(r);
            double avgPrice = r.getAvgPrice();
            String city = r.getLocation().getCityName();
            System.out.println(indexPos + " | " + r.getName() + " | " + r.getGenre() + " | " + r.getRating() + " | "
                    + city + " | " + numberFormat.format(distance) + "km" + "\n Average Price: $" + avgPrice);
        }
        System.out.println("\n Enter Anything in the console to return to menu");
        input.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: lists out info about a restaurant
    private void doInfo(int pos) {
        while (pos < 0 || pos >= database.getProcessedDataBase().size()) {
            System.out.println("\n Please enter index of restaurant:");
            pos = Integer.parseInt(input.nextLine());
            input.reset();
            if (pos > database.getProcessedDataBase().size()) {
                System.out.println("\n Index is out of bounds.");
            }
        }
        Restaurant r = database.getProcessedDataBase().get(pos);
        seeRestaurant(r);
        System.out.println("\n Toggle favourite? - fav");
        System.out.println("\n See menu? - see");
        System.out.println("\n Add to menu? - add");
        String userInput = input.nextLine();
        if (userInput.equals("see")) {
            seeMenu(r);
        } else if (userInput.equals("add")) {
            addToMenu(r);
        } else if (userInput.equals("fav")) {
            r.toggleFavourite();
            doInfo(pos);
        }
        input.reset();
    }

    // MODIFIES: this
    // EFFECTS: Adds new restaurant to database
    private void doAdd() {
        System.out.println("\n Enter Restaurant Name: ");
        String name = input.nextLine();
        System.out.println("\n Enter Genre Name: ");
        String genre = input.nextLine();
        System.out.println("\n Enter Rating (#/10): ");
        int rating = Integer.parseInt(input.nextLine());
        System.out.println("\n Enter City Name: ");
        String cityName = input.nextLine();
        System.out.println("\n Enter X coordinate: ");
        double coordX = Double.parseDouble(input.nextLine());
        System.out.println("\n Enter Y coordinate: ");
        double coordY = Double.parseDouble(input.nextLine());

        Location location = new Location(cityName, coordX, coordY);
        Restaurant restaurant = new Restaurant(name, genre, rating, location);
        database.addRestaurant(restaurant);
        database.resetProcessedDataBase();
        System.out.println("Added new restaurant!");
    }

    // EFFECTS: shows restaurant info
    private void seeRestaurant(Restaurant r) {
        double distance = r.getDistance(currLocation);
        String city = r.getLocation().getCityName();
        double avgPrice = r.getAvgPrice();
        System.out.println("\n Name: " + r.getName() + "\n Genre: " + r.getGenre() + "\n Rating: "
                + r.getRating() + "\n City: " +  city + "\n Distance:" + distance + "\n Average Price: "
                + avgPrice + "\n Favourite: " + r.isFavourite());
    }

    // EFFECTS: shows restaurant menu list
    private void seeMenu(Restaurant r) {
        System.out.println("\n Menu:");
        for (MenuItem food: r.getMenu()) {
            System.out.println(food.toString());
        }
        System.out.println("\n Enter Anything in the console to return to menu");
        input.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: new dish to menu
    private void addToMenu(Restaurant r) {
        boolean keepAdding = true;

        while (keepAdding) {
            System.out.println("\n Enter Dish Name: ");
            String name = input.nextLine();
            System.out.println("\n Enter Price: ");
            double price = Double.parseDouble(input.nextLine());

            MenuItem item = new MenuItem(name, price);
            r.addToMenu(item);
            System.out.println("Updated Menu! Add another? y/n");
            if (!input.nextLine().equals("y")) {
                keepAdding = false;
            }
        }
    }

    // EFFECTS: chooses random dish
    private void doRandom() {
        Restaurant random;
        String preference;
        System.out.println("\n Randomized from processed? y/n");
        preference = input.nextLine();
        if (preference.equals("y")) {
            random = database.randomRestaurant(database.getProcessedDataBase());
        } else {
            random = database.randomRestaurant(database.getDataBase());
        }
        System.out.println("\n We found a restaurant: " + random.getName());
        System.out.println("\n See info? y/n");
        preference = input.nextLine();
        input.reset();
        if (preference.equals("y")) {
            doInfo(database.getDataBase().indexOf(random));
        } else {
            System.out.println("\n Randomize again? y/n");
            preference = input.nextLine();
            if (preference.equals("y")) {
                doRandom();
            }
        }
    }

    // EFFECTS: sorts the database by a user input specified field from Restaurant
    private void doSort() {
        String sortBy = "";
        while (!(sortBy.equals("name") || sortBy.equals("rating") || sortBy.equals("price"))) {
            System.out.println("Sort Options:");
            System.out.println("\t - Name");
            System.out.println("\t - Rating");
            System.out.println("\t - Price");
            sortBy = input.nextLine();
            sortBy.toLowerCase();
        }
        String order = "";
        while (!(order.equals("a") || order.equals("d"))) {
            System.out.println("Sort Options:");
            System.out.println("\t a - ascending");
            System.out.println("\t d - descending");
            order = input.nextLine();
            order.toLowerCase();
        }
        if (order.equals("a")) {
            database.sortDatabaseAscending(sortBy);
        } else {
            database.sortDatabaseDescending(sortBy);
        }
    }
}
