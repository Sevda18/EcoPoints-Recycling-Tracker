# EcoPoints Recycling Tracker

A Java console application that allows households to log recycling events and earn eco points.

## Purpose
This project practices:
- Object-Oriented Programming (OOP)
- Java collections (`HashMap`, `ArrayList`)
- Java Date & Time API
- File I/O and serialization
- Exception handling
- Console-based menu navigation

## Features
- Register households with a unique integer ID
- Add recycling events (material, weight in kg, date)
- Automatic eco points calculation (10 points per 1 kg)
- Display:
  - all registered households
  - all events for a selected household
  - total recycled weight and total points per household
- Reports:
  - household with the highest points
  - total community recycled weight
- Save and load data from a `.dat` file (Java serialization)

