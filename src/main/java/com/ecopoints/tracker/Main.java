package com.ecopoints.tracker;

public class Main {
    public static void main(String[] args) {
        EcoPointsService service = new EcoPointsService();
        ConsoleMenu menu = new ConsoleMenu(service);
        menu.start();
    }
}
