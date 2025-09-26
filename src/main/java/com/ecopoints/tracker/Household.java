package com.ecopoints.tracker;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Household implements Serializable {
    private int id;
    private String name;
    private String address;
    private LocalDateTime joinDate;
    private double points;
    List<RecyclingEvent> events = new java.util.ArrayList<>();

    static private int nextId = 0;

    static private int getNextId(){
        return nextId++;
    }

    public Household(String name, String address){
        this.id = getNextId();
        this.name = name;
        this.address = address;
        this.joinDate = LocalDateTime.now();
        this.points = 0;
    }

    public int getId() {
        return id;
    }

    public double getPoints(){
        return points;
    }

    public void addPoints(double points){
        this.points += points;
    }

    public String getName(){
        return name;
    }

    public String getAdress(){
        return address;
    }

    public LocalDateTime getJoinDate(){
        return joinDate;
    }

    public void addEvent(RecyclingEvent e){
        events.add(e);
        addPoints(e.getPointsEarned());
    }
}
