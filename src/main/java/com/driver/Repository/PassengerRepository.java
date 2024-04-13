package com.driver.Repository;


import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class PassengerRepository {
    HashMap<Integer, Passenger> passengerDB = new HashMap<>();
    HashMap<Integer, Integer> bookFlights = new HashMap<>();

    public void addPassenger(Passenger passenger){
        int key = passenger.getPassengerId();
        if(!passengerDB.containsKey(key))
            passengerDB.put(key, passenger);
    }

    public Passenger getPassenger(int passengerID){
        return passengerDB.getOrDefault(passengerID, null);
    }


    public int getTotalNumberOfPassengers() {
        return passengerDB.size();
    }


    public void bookTicket(int passengerId, int flightId){
        if(passengerDB.containsKey(passengerId)){
            bookFlights.put(passengerId, flightId);
        }
    }

    public int getFlightIdForPassenger(int passengerId){
        return bookFlights.getOrDefault(passengerId, -1);
    }

    public void cancleTicket(int passengerId){
        bookFlights.remove(passengerId);
    }
}
