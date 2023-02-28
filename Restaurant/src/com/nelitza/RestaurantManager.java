package com.nelitza;

import com.nelitza.restaurant.Restaurant;


/**
 * Restaurant manager serves as a simulation of how the manager interacts with the system.
 *
 * The Manager in the scenario is an outside actor because realistically a manager cannot be everywhere when needed
 * most likely employees can assign customers to table and give customers the estimated wait time for a given table.
 */
public class RestaurantManager {

    public static void main(String[] args){

        Restaurant restaurant = new Restaurant();

        System.out.println("Welcome");

        System.out.println("\n" +restaurant.getTimeBreakDownPerTable());

        System.out.println("************** Assign 2 people to table 1. ******************");
        restaurant.assignCustomersToTable(2,1);
        System.out.println("\n" +restaurant.getTimeBreakDownPerTable());

        System.out.println("************** Assign 6 people to any table. ******************");
        restaurant.assignCustomersToAnyTable(6);
        System.out.println("\n" +restaurant.getTimeBreakDownPerTable());

        try {
            Thread.currentThread().sleep(2 * 60000);
        } catch (InterruptedException e) { }
        System.out.println("************** Is there a table available? ******************");
        System.out.println("\n" +restaurant.getTimeBreakDownPerTable());
    }
}
