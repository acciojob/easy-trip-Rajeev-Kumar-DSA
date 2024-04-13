package com.driver.Repository;


import com.driver.model.Airport;
import com.driver.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class AirportRepository {
    // Here I will maintain - data storage (HashMap)
    // Unique variable - I will consider as a key .
    HashMap<String, Airport> airportDB = new HashMap<>();

    // add the airportDB details here  -> for airportDB class
    public void addAirport(Airport airport){
        // get the unique key first
        String key = airport.getAirportName();
        // now I will store in DB
        airportDB.put(key, airport);  // respective key -> and airportDB details
    }


    // get the airportDB details - for respective flightId
    public Airport getAirport(String airportName){
        return airportDB.getOrDefault(airportName, null);
    }


    public List<Airport> getAllAirport(){
        List<Airport> airports = new ArrayList<>();
        for(Airport airport : airportDB.values()){
            airports.add(airport);
        }
        return airports;
    }
}
