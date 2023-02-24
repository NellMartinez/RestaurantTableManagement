package com.nelitza;

import com.nelitza.restaurant.Restaurant;

import java.util.Timer;


/**
 * Restaurant manager serves as a simulation of how the manager interacts with the system.
 *
 * The Manager in the scenario is an outside actor because realistically a manager cannot be everywhere when needed
 * most likely employees can assign customers to table and give customers the estimated wait time for a given table.
 */
public class RestaurantManager {

    public static void main(String[] args){

        Timer timer = new Timer();

        ////////////////
        Restaurant restaurant = new Restaurant();

        System.out.println("Welcome");

        System.out.println(restaurant.getTimeBreakDownPerTable());

    }
}
