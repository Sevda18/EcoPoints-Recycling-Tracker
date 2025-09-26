package com.ecopoints.tracker;

import java.io.*;
import java.util.HashMap;

public class FileUtils {
    public static void saveData(HashMap<Integer, Household> households, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(households);
        } catch (IOException e) {
            System.out.println("Error saving data " + e.getMessage());
        }
    }

    public static HashMap<Integer, Household> loadData(String filename){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            return (HashMap<Integer, Household>) in.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Error loading data " + e.getMessage());
            return new HashMap<>();
        }
    }


}

