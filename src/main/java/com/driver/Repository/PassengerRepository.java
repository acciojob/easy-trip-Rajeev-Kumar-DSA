package com.driver.Repository;


import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class PassengerRepository {
    HashMap<Integer, Passenger> passengerDB = new HashMap<>();

    public void addPassenger(Passenger passenger){
        int key = passenger.getPassengerId();
        passengerDB.put(key, passenger);
    }

    public Passenger getPassenger(int passengerID){
        return passengerDB.getOrDefault(passengerID, null);
    }
}
