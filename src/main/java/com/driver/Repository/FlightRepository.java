package com.driver.Repository;


import com.driver.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FlightRepository {
    HashMap<Integer, Flight> flightDB = new HashMap<>();

    // add the flight details here  -> for flight class
    public void addFlight(Flight flight){
        // get the unique key first
        int key = flight.getFlightId();
        // now I will store in DB
        flightDB.put(key, flight);  // respective key -> and flight details
    }


    // get the flight details - for respective flightId
    public Flight getFlight(int flightId){
        return flightDB.getOrDefault(flightId, null);
    }
}
