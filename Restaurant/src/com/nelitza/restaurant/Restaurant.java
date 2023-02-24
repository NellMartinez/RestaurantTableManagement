package com.nelitza.restaurant;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In the traditional sense a Restaurant (in the real life) has tables and a maximum capacity thus in here
 * we demonstrate that real life scenario of maximum capacity scenario according to county regulations.
 *
 * In terms of real life scenario a manager is an external actor thus a manager action can be modeled by the manager class (assigning people to tables)
 * but an employee can get time remaining for a table.
 */
public class Restaurant {

    private List<Table> tables;
    private final int MAXIMUM_RESTAURANT_CAPACITY = 18;

    /**
     * The constructor for the restaurant. According to the scenario the restaurant has 18 TABLES
     * but this constructor allows for a variable amount in case of a special event, but not to exceed 18 tables
     * as per county regulations
     */
    public Restaurant() {
        this.tables = new ArrayList<>();
        if (MAXIMUM_RESTAURANT_CAPACITY <= 18) {
            for (int i = 0; i < MAXIMUM_RESTAURANT_CAPACITY; i++) {

                this.tables.add(new Table(getChairs(), i));
            }
        }

    }

    /**
     * Returns the list of all available tables
     * @return {@link Table}
     */
    public List<Table> getAvailableTables(){
        List<Table> availableTables = new ArrayList<>();
        for(Table table: this.tables){
            if(table.isAvailable()){
                availableTables.add(table);
            }
        }
        return availableTables;
    }


    /**
     * Returns the table breakdown in ascending order for when all tables are occupied and a customer asks when the next table
     * will be available
     * @return {@link Table}
     */
    public List<Table> getTimeBreakDownPerTable(){
        List<Table> sortedList = this.tables.stream()
                .sorted(Comparator.comparing(Table::getNextAvailability).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }

    /**
     * Manager assigns customers to a table with a custom time for the customers
     * to be present at the table, gives reservation time.
     * @param customers
     * @param minutesForReservation
     */
    public void addCustomersToTable(int customers, int minutesForReservation){
        List<Table> availableTables = getAvailableTables();

        for(Table table: availableTables){
            if (table.isAvailable() && table.getChairs() >= customers){
                table.setTimeSlot(new Reservation(minutesForReservation));
                table.setCapacity(customers);
            }
        }
    }



    /**
     * Manager assigns customers to a table with a pre-set time according to the amount of people sitting at that table
     * @param customers
     */
    public void addCustomersToTable(int customers){
        List<Table> availableTables = getAvailableTables();

        for(Table table: availableTables){
            if (table.isAvailable() && table.getChairs() >= customers){
                table.setTimeSlot(customers);
                table.setCapacity(customers);
            }
        }

    }

    /**
     * A manager can remove a table if merging with another one or just remove it because the table may be broken...
     */
    public void removeTable(){
        throw new NotImplementedException();
    }

    /**
     * A table can be added
     */
    public void addTable() {
        throw new NotImplementedException();
    }

    /**
     * Sometimes you can have two tables of 2 chairs and there is a party of 4 coming to the restaurant, a manager can merge tables
     * @param tableOne
     * @param table2
     */
    public void mergeTables(Table tableOne, Table table2){
        throw new NotImplementedException();
    }

    /**
     * Utility method to randomly assign chairs to each table.
     * @return
     */
    private int getChairs(){
        Random r = new Random();
        int low = 1;
        int high = 9;
        int result = r.nextInt(high-low) + low;

        return result;
    }
}
