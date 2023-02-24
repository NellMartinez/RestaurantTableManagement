package com.nelitza.restaurant;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Classes should only have one reason to change, for this class its only responsability is to calculate the time and
 * keep track of minutes remaining in its reservation.
 */
public class Reservation {

    private LocalDateTime startTime;
    private LocalDateTime stopTime;


    public Reservation(){

    }

    public Reservation(int minutesAllocatedToCurrentReservation){
        this.startTime = LocalDateTime.now();
        this.stopTime = this.startTime.plus(minutesAllocatedToCurrentReservation, ChronoUnit.MINUTES);

    }


    public long getMinutesRemaining(){
        if (this.startTime == null){
            return 0;
        }
        long remainingMinutes = ChronoUnit.MINUTES.between(
                Instant.now(), stopTime);

        return remainingMinutes;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

}
