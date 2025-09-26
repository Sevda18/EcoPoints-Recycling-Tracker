package com.ecopoints.tracker;

import java.util.Scanner;

import static java.nio.file.Files.readString;

public class ConsoleMenu {
    private EcoPointsService service;
    private Scanner scanner;

    public ConsoleMenu(EcoPointsService service){
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        boolean running = true;

        while(running){
            printMenu();
            int choice = readInt("Select an option: ");

            switch(choice){
                case 1:
                    registerHousehold();
                    break;
                case 2:
                    addRecyclingEvent();
                    break;
                case 3:
                    viewAllHouseholds();
                    break;
                case 4:
                    viewEventsForHousehold();
                    break;
                case 5:
                    viewHouseholdPoints();
                    break;
                case 6:
                    viewTopHousehold();
                    break;
                case 7:
                    viewCommunityPoints();
                    break;
                case 8:
                    saveDataToFile();
                    break;
                case 9:
                    loadDataFromFile();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== EcoPoints Recycling Tracker ===");
        System.out.println("1. Register a new household");
        System.out.println("2. Add a recycling event");
        System.out.println("3. View all households");
        System.out.println("4. View events for a household");
        System.out.println("5. Show total points for a household");
        System.out.println("6. Household with the highest points");
        System.out.println("7. Total recycled materials in the community");
        System.out.println("8. Save data to file");
        System.out.println("9. Load data from file");
        System.out.println("0. Exit");
    }

    private void registerHousehold(){
        System.out.println("\n--- Register a New Household ---");
        String name = readString("Enter household name: ");
        String address = readString("Enter household address: ");

        Household h = new Household(name, address);
        service.registerHousehold(h);
        System.out.println("Household registered with ID: " + h.getId());
    }

    private void addRecyclingEvent(){
        System.out.println("\n--- Add a Recycling Event ---");
        int householdId = readInt("Enter household ID: ");
        MaterialType type = readMaterialType("Enter material type (PLASTIC, PAPER, GLASS, METAL): ");
        double weight = readDouble("Enter weight in kg: ");

        Household h;
        try {
            h = service.getHousehold(householdId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        RecyclingEvent event = new RecyclingEvent(type, weight);
        h.addEvent(event);
        System.out.println("Recycling event added. Points earned: " + event.getPointsEarned());
    }

    private void viewHouseholdPoints(){
        System.out.println("\n View Household Points");
        int householdId = readInt("Enter household ID: ");
        try{
            Household h = service.getHousehold(householdId);
            System.out.println("Household " + h.getName() + " has " + h.getPoints() + " points.");
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private void viewCommunityPoints(){
        double totalPoints = service.getTotalRecyclingPointsCommunity();
        System.out.println("Total recycling points in the community: " + totalPoints);
    }

    private Household viewTopHousehold() {
        Household h = service.getHeightestPointHousehold();
        if (h != null) {
            System.out.println("Household with the highest points: " + h.getName() +
                    " (" + h.getPoints() + " points)");
        } else {
            System.out.println("No households registered.");
        }
        return h;
    }

    public int readInt(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public double readDouble(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextDouble()){
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }

        return scanner.nextDouble();
    }

    public MaterialType readMaterialType(String prompt){
        System.out.println(prompt);
        while(true){
            String input = scanner.next().toUpperCase();
            try{
                return MaterialType.valueOf(input);
            }
            catch(IllegalArgumentException e){
                System.out.println("Invalid material type. Please enter PLASTIC, PAPER, GLASS, or METAL.");
            }
        }
    }

    public String readString(String prompt){
        System.out.println(prompt);
        return scanner.next();
    }

    private void viewAllHouseholds(){
        service.printAllHouseholds();
    }

    private void viewEventsForHousehold(){
        System.out.println("\n View Events for a Household");
        int householdId = readInt("Enter household ID: ");
        try{
            service.printEventPerHousehold(householdId);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private void saveDataToFile() {
        String filename = readString("Enter filename to save data:");
        try{
            service.saveData(filename);
            System.out.println("Data saved to "+filename);
        }
        catch(Exception e){
            System.out.println("Error saving data" + e.getMessage());
        }
    }

    private void loadDataFromFile() {
        String filename = readString("Enter filename to load data:");
        try{
            service.loadData(filename);
            System.out.println("Data loaded from "+filename);
        }
        catch(Exception e){
            System.out.println("Error loading data" + e.getMessage());
        }
    }
}
