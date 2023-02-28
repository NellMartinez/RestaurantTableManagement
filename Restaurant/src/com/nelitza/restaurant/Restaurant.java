package com.nelitza.restaurant;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In the traditional sense a Restaurant (in the real life) has tables and a maximum capacity thus in here
 * we demonstrate that real life scenario of maximum capacity scenario according to county regulations.
 *
 * In terms of real life scenario a manager is an external actor thus a manager action can be modeled by a manager class (assigning people to tables)
 * but any employee can get time remaining for a table. For this reason we are using a simulation in RestaurantManager, but we don't have a manager class.
 */
public class Restaurant {

    private List<Table> tables;
    private final int MAXIMUM_RESTAURANT_CAPACITY = 18;

    /**
     * The constructor for the restaurant. According to the scenario the restaurant has 18 TABLES
     * as per county regulations
     */
    public Restaurant() {
        this.tables = new ArrayList<>();
        if (MAXIMUM_RESTAURANT_CAPACITY <= 18) {
            for (int i = 0; i < MAXIMUM_RESTAURANT_CAPACITY; i++) {

                this.tables.add(new Table(getChairs(), i+1));
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
                .sorted(Comparator.comparing(Table::getNextAvailability))
                .collect(Collectors.toList());
        return sortedList;
    }

    /**
     * Manager assigns customers to a table with a custom time for the customers
     * to be present at the table, gives reservation time.
     * @param customers
     * @param minutesForReservation
     */
    public void assignCustomersToAnyTableGivenMinutes(int customers, int minutesForReservation){
        List<Table> availableTables = getAvailableTables();

        for(Table table: availableTables){
            if (table.isAvailable() && table.getChairs() >= customers){
                table.assignCustomersWithMinutesPreset(new Reservation(minutesForReservation));
                table.setCapacity(customers);
            }
        }
    }

    /**
     * Manager assigns customers to a table with a custom time for the customers
     * to be present at the table, gives reservation time and table number
     * @param customers
     * @param minutesForReservation
     */
    public void assignCustomersToTableWithReservationMinutes(int customers, int tableNumber, int minutesForReservation){
        List<Table> availableTables = getAvailableTables();

        for(Table table: availableTables){
            if (table.isAvailable() && table.getChairs() >= customers){
                table.assignCustomersWithMinutesPreset(new Reservation(minutesForReservation));
                table.setCapacity(customers);
            }
        }
    }

    /**
     * Manager assigns customers to a table with a custom time for the customers
     * to be present at the table, gives reservation time and table number
     * @param customers
     * @param tableNumber
     * @return
     */
    public void assignCustomersToTable(int customers, int tableNumber){
        List<Table> availableTables = getAvailableTables();
        Optional<Table> table = availableTables.stream().filter(t -> t.getTableNumber() == tableNumber && t.getChairs() >= customers).findAny();

        if (table.isPresent()){
            table.get().assignCustomersWithMinutesPreset(customers);
        }


    }


    /**
     * Manager assigns customers to a table with a pre-set time according to the amount of people sitting at that table
     * @param customers
     */
    public void assignCustomersToAnyTable(int customers){
        List<Table> availableTables = getAvailableTables();

        for(Table table: availableTables){
            if (table.isAvailable() && table.getChairs() >= customers){
                table.assignCustomersWithMinutesPreset(customers);
                table.setCapacity(customers);
                break;
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
