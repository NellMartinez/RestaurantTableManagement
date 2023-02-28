package com.nelitza.restaurant;


/**
 * A table has chairs which can be correlated to the capacity to the table. For example a table with 9 chairs can have a current capacity of 2, because
 * 2 customers are sitting at that table, if a table has 9 chairs and only 2 customers on it
 * it will determine the pre-set time for the customers to stay in the restaurant at that given table.
 *
 * In order to keep track of available tables, each table is initialized with a Reservation object.
 */
public class Table {

    private int tableNumber;

    private int chairs; // the amount of chairs a table can have this varies by size of table

    private int capacity; // the amount of chairs occupied. If this number is not zero and the timer says there should
    //not be anyone here then the table will qualify as unavailable.

    private Reservation timeSlot; //Keeps track of when a table will be available

    private final int PRESET_TIME_FOR_TWO = 2;

    private final int PRESET_TIME_FOR_NINE= 2;

    public Table(int chairs, int tableNumber){
        this.tableNumber = tableNumber;
        this.chairs = chairs;
        this.timeSlot = new Reservation();

    }


    /**
     * Gets the availability given a tables reservation attribute.
     * @return
     */
  public boolean isAvailable(){
        if(this.capacity == 0 && timeSlot.getMinutesRemaining() == 0 ){
            return true;
      }
        else{
            return false;
        }
  }

    /**
     * Gets the next availability for a given Table
     * @return
     */
  public int getNextAvailability() {


          return this.timeSlot.getMinutesRemaining();

  }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void assignCustomersWithMinutesPreset(Reservation timeSlot) {
        this.timeSlot = timeSlot;
    }

    /**
     * Sets the time given presets
     * @param customers
     */
    public void assignCustomersWithMinutesPreset(int customers) {
      if (customers <= 3){
          this.timeSlot = new Reservation(PRESET_TIME_FOR_TWO);
      }
      else{
          this.timeSlot = new Reservation(PRESET_TIME_FOR_NINE);
      }

    }

    public int getChairs() {
        return chairs;
    }

    public int getCapacity() {
        return capacity;
    }

    public Reservation getTimeSlot() {
        return timeSlot;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public String toString(){
        return "Table # "+(this.tableNumber)+" has an remaining wait time of: " + getNextAvailability() + " minutes and has " + getChairs() + " chairs."+ "\n";
    }
}
