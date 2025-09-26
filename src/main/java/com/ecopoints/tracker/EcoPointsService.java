package com.ecopoints.tracker;

import java.util.HashMap;

public class EcoPointsService {
    private HashMap<Integer, Household> households = new HashMap<>();

    public void registerHousehold(Household h){
        if(households.containsKey(h.getId()))
        {
            throw new IllegalArgumentException("Household with this ID already registered.");
        }

        households.put(h.getId(), h);
    }

    public Household getHousehold(int id){
        if(!households.containsKey(id)){
            throw new IllegalArgumentException("Household with this id not found");
        }

        return households.get(id);
    }

    public void printAllHouseholds() {
        if(households.isEmpty()){
            System.out.println("No households registered.");
            return;
        }
        for (Household h : households.values()) {
            System.out.println(h.getId() + " - " + h.getName() +
                    " (" + h.getPoints() + " points)");
        }
    }

    public Household getHeightestPointHousehold(){
        Household top = null;
        for(Household h : households.values()){
            if(top == null || h.getPoints() > top.getPoints())
            {
                top = h;
            }
        }

        return top;
    }

    public double getTotalRecyclingPointsCommunity(){
        double weight = 0;
        for(Household h : households.values()){
            weight += h.getPoints();
        }

        return weight;
    }

    public double getTotalRecyclingPointsByHousehold(int id){
        if(!households.containsKey(id)){
            throw new IllegalArgumentException("Household with this id not found");
        }

        return households.get(id).getPoints();
    }

    public void printEventPerHousehold(int id){
        if(!households.containsKey(id)){
            throw new IllegalArgumentException("Household with this id not found");
        }

        Household h = households.get(id);
        System.out.println("Recycling events for household " + h.getName() + ":");
        for(RecyclingEvent e : h.events){
            System.out.println("- " + e.getPointsEarned() + " points on " + e.getDateOfRecycling());
        }
    }

    public void saveData(String filename){
        FileUtils.saveData(households, filename);
    }

    public void loadData(String filename){
        households = FileUtils.loadData(filename);
    }
}
