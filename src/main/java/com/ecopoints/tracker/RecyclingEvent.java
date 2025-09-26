package com.ecopoints.tracker;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RecyclingEvent implements Serializable {
    private MaterialType type;
    private double weight;
    private LocalDateTime dateOfRecycling;
    private double pointsEarned;

    public RecyclingEvent(MaterialType type, double weight){
        if(weight <= 0){
            throw new IllegalArgumentException("Weight must be greater than 0.");
        }
        this.type = type;
        this.weight = weight;
        this.dateOfRecycling = LocalDateTime.now();
        this.pointsEarned = getPointsEarned();
    }

    public double getPointsEarned(){
        return (weight *10);
    }

    public LocalDateTime getDateOfRecycling(){
        return dateOfRecycling;
    }
}
