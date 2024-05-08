

//import model.Place;
//import model.map.Map2D;
//import model.services.Service;
//import view.MapView;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;

/*Flow:
 User add place to the map -> the System will encode the place coordinate and add to the database for
 specific service -> if user want to find poi of specific service -> system will search in that data base*/

import controller.GeoHash;
import model.map.Map2D;
import model.services.*;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GeoHash manager = new GeoHash();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Place");
            System.out.println("2. Find Place");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of places you want to add: ");
                    int count = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    for (int i = 0; i < count; i++) {
                        System.out.print("Enter place details (type,name, x, y): ");
                        String details = scanner.nextLine();
                        String[] parts = details.split(",\\s*");

                        if (parts.length < 4) {
                            System.out.println("Invalid input. Try again.");
                            i--; // Retry the current input
                            continue;
                        }

                        String type = parts[0].trim();
                        String name = parts[1].trim(); // New name input
                        int x = Integer.parseInt(parts[2].trim());
                        int y = Integer.parseInt(parts[3].trim());

                        switch (type.toLowerCase()) {
                            case "atm":
                                ATM data = new ATM(new Map2D(x, y), name);
                                manager.addATM(data);
                                break;
                            case "coffee shop":
                                CoffeShop dataCoffeShop = new CoffeShop(new Map2D(x, y),name);
                                manager.addCoffeeShop(dataCoffeShop);
                                break;
                            case "gas station":
                                GasStation gasStationData = new GasStation(new Map2D(x, y),name);
                                manager.addGasStation(gasStationData);
                                break;
                            case "hospital":
                                Hospital hospitalData = new Hospital(new Map2D(x, y),name);
                                manager.addHospital(hospitalData);
                                break;
                            case "restaurant":
                                Restaurant restaurantData = new Restaurant(new Map2D(x, y),name);
                                manager.addRestaurant(restaurantData);
                                break;
                            default:
                                System.out.println("Unknown type. Try again.");
                                i--; // Retry the current input
                                break;
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter the type the place (type): ");
                    String searchDetails = scanner.nextLine();
                    String[] searchParts = searchDetails.split(",\\s*");

                    if ("ATM".equalsIgnoreCase(searchParts[0])) {
                        manager.printPlaces(manager.findATMs()); // print found data
                    } else if("CoffeeShop".equalsIgnoreCase(searchParts[0])) {
                        manager.printPlaces(manager.findCoffeeShops()); // print found data
                    } else if("GasStation".equalsIgnoreCase(searchParts[0])) {
                        manager.printPlaces(manager.findGasStation()); // print found data
                    } else if("Hospital".equalsIgnoreCase(searchParts[0])) {
                        manager.printPlaces(manager.findHospital()); // print found data
                    } else if("Restaurant".equalsIgnoreCase(searchParts[0])) {
                        manager.printPlaces(manager.findRestaurant()); // print found data
                    }else {
                        // Handle other types or show an error
                        System.out.println("Type not supported or invalid input.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

        scanner.close();
    }
}
