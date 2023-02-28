package com.nelitza.restaurant;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classes should only have one reason to change, for this class its only responsibility is to calculate the time and
 * keep track of minutes remaining in its reservation and to cancel the timer for when the minutes remaining expired (to release the table to new patrons).
 */
public class Reservation {

    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private Timer timer;

    public Reservation(int minutesAllocatedToCurrentReservation){
        this.startTime = LocalDateTime.now();
        this.stopTime = this.startTime.plus(minutesAllocatedToCurrentReservation, ChronoUnit.MINUTES);
        this.timer = new Timer();
        this.timer.schedule(new ReservationTask(), getMinutesRemaining() * 60000);

    }


    class ReservationTask extends TimerTask {
        @Override
        public void run() {
            timer.cancel();
        }
    }

    public int getMinutesRemaining(){
        if (this.startTime == null){
            return 0;
        }
        int remainingMinutes = (int) ChronoUnit.MINUTES.between(
                LocalDateTime.now(), stopTime);

        return remainingMinutes;
    }

}
