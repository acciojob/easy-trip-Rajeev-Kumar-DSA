package com.driver.Repository;


import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class PassengerRepository {
    HashMap<Integer, Passenger> passengerDB = new HashMap<>();
    HashMap<Integer, Integer> bookFlights = new HashMap<>();

    FlightRepository flightRepository = new FlightRepository();

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

        // if the passenger exists in the passenger database
        if(!passengerDB.containsKey(passengerId)){
            return;
        }

        // if the flight exists in the flight database (optional, depending on your design)
        if(!flightRepository.containsFlight(flightId)){
            return;
        }

        // Check if the passenger already has a booked flight (optional, depending on your design)
        if (bookFlights.containsKey(passengerId)){
            return;
        }

        bookFlights.put(passengerId, flightId);
    }

    public int getFlightIdForPassenger(int passengerId){
        return bookFlights.getOrDefault(passengerId, -1);
    }

    public void cancleTicket(int passengerId){
        bookFlights.remove(passengerId);
    }
}
