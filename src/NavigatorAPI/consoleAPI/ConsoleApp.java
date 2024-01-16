package NavigatorAPI.consoleAPI;

import NavigatorAPI.navigator.Navigator;
import NavigatorAPI.navigator.NavigatorImpl;
import NavigatorAPI.routes.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApp {
    private static final Navigator navigator = new NavigatorImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        samplesChose();

        while (true) {
            printMenu();

            actionChose();
        }
    }

    public static void samplesChose() {
        separation();
        System.out.println("Create Sample Routes?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> addSampleRoutes();
            case 2 -> System.out.println();
            default -> System.out.println("It seems you enter a wrong number. No samples chosen by default");
        }
    }

    public static void actionChose() {
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> addRoute();
            case 2 -> removeRoute();
            case 3 -> searchRoutes();
            case 4 -> getFavoriteRoutes();
            case 5 -> getTop3Routes();
            case 6 -> containsRoute();
            case 7 -> getRouteByID();
            case 8 -> chooseRouteByID();
            case 9 -> countOfRoutes();
            case 10 -> {
                System.out.println("Exiting the application. Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private static void countOfRoutes() {
        separation();
        System.out.println("Count of Routes is: " + navigator.size());
    }

    private static void chooseRouteByID() {
        separation();
        System.out.println("Enter Route details:");

        System.out.print("ID: ");
        String id = scanner.next();
        navigator.chooseRoute(id);
        System.out.println("Route Chosen");
    }

    private static void getRouteByID() {
        separation();
        System.out.println("Enter Route details:");

        System.out.print("ID: ");
        String id = scanner.next();

        System.out.println(navigator.getRoute(id));
    }

    private static void containsRoute() {
        separation();
        System.out.println("Enter Route details:");

        System.out.print("ID: ");
        String id = scanner.next();

        System.out.print("Distance: ");
        double distance = scanner.nextDouble();

        System.out.print("Popularity: ");
        int popularity = scanner.nextInt();

        System.out.print("Is Favorite (true/false): ");
        boolean isFavorite = scanner.nextBoolean();

        System.out.print("Number of Location Points: ");
        int numPoints = scanner.nextInt();

        String[] locationPoints = new String[numPoints];
        for (int i = 0; i < numPoints; i++) {
            System.out.print("Enter Location Point " + (i + 1) + ": ");
            locationPoints[i] = scanner.next();
        }

        System.out.println(navigator.contains(createRoute(id, distance, popularity, isFavorite, locationPoints)));
    }

    public static void separation() {
        System.out.println("-----------------------------------------------");

    }

    private static void printMenu() {
        separation();
        System.out.println("Navigator Console App:");
        System.out.println("1. Add Route");
        System.out.println("2. Remove Route");
        System.out.println("3. Search Routes");
        System.out.println("4. Get Favorite Routes");
        System.out.println("5. Get Top 3 Routes");
        System.out.println("6. Check Route by ID");
        System.out.println("7. Find Route by ID");
        System.out.println("8. Chose Route By ID");
        System.out.println("9. Count of Routes");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");

    }

    private static void addRoute() {
        separation();
        System.out.println("Enter Route details:");

        System.out.print("ID: ");
        String id = scanner.next();

        System.out.print("Distance: ");
        double distance = scanner.nextDouble();

        System.out.print("Popularity: ");
        int popularity = scanner.nextInt();

        System.out.print("Is Favorite (true/false): ");
        boolean isFavorite = scanner.nextBoolean();

        System.out.print("Number of Location Points: ");
        int numPoints = scanner.nextInt();

        String[] locationPoints = new String[numPoints];
        for (int i = 0; i < numPoints; i++) {
            System.out.print("Enter Location Point " + (i + 1) + ": ");
            locationPoints[i] = scanner.next();
        }


        navigator.addRoute(createRoute(id, distance, popularity, isFavorite, locationPoints));
        System.out.println("Route added successfully!");
    }

    private static void removeRoute() {
        separation();
        System.out.print("Enter Route ID to remove: ");
        String routeId = scanner.next();
        navigator.removeRoute(routeId);
        System.out.println("Route removed successfully!");
    }

    private static void searchRoutes() {
        separation();
        System.out.print("Enter Start Point: ");
        String startPoint = scanner.next();

        System.out.print("Enter End Point: ");
        String endPoint = scanner.next();

        Iterable<Route> result = navigator.searchRoutes(startPoint, endPoint);
        printRoutes("Search Result", result);
    }

    private static void getFavoriteRoutes() {
        separation();
        System.out.print("Enter Destination Point: ");
        String destinationPoint = scanner.next();

        Iterable<Route> result = navigator.getFavoriteRoutes(destinationPoint);
        printRoutes("Favorite Routes", result);
    }

    private static void getTop3Routes() {
        separation();
        Iterable<Route> result = navigator.getTop3Routes();
        printRoutes("Top 3 Routes", result);
    }


    private static void printRoutes(String title, Iterable<Route> routes) {
        separation();
        System.out.println(title + ":");
        for (Route route : routes) {
            System.out.println(route);
        }
        System.out.println();
    }

    private static void addSampleRoutes() {
        Route route1 = createRoute("1", 10.0, 7, true, "A", "B", "C");
        Route route2 = createRoute("2", 3.0, 6, false, "D", "E");
        Route route3 = createRoute("3", 4.0, 6, true, "A", "C", "D", "E", "F");
        Route route4 = createRoute("4", 10.0, 6, true,  "C", "D", "E", "F");

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);
    }

    private static Route createRoute(String id, double distance, int popularity, boolean isFavorite, String... locationPoints) {
        Route route = new Route();
        route.setId(id);
        route.setDistance(distance);
        route.setPopularity(popularity);
        route.setFavorite(isFavorite);
        route.setLocationPoints(new ArrayList<>(Arrays.asList(locationPoints)));
        return route;
    }
}

