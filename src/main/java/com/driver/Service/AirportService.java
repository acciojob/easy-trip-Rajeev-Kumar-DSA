package com.driver.Service;


import com.driver.Repository.AirportRepository;
import com.driver.model.Airport;
import com.driver.model.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {
    // Here I will contain the ->  business logic of our application
    // maintain here all respective code in java
    // here I will call the repository first then get the data in list then solve according to question

    AirportRepository airportRepository = new AirportRepository();

    public void addAirportService(Airport airport){
        airportRepository.addAirport(airport);
    }

    public String getLargestAirportNameService(){
        List<Airport> airports = airportRepository.getAllAirport();

        if (airports.isEmpty()){
            return null;
        }
        Airport largestAirportName = null;

        for(Airport airport : airports){
            if (largestAirportName == null || airport.getNoOfTerminals() > largestAirportName.getNoOfTerminals()){
                largestAirportName = airport;
            }
        }

        if (largestAirportName == null){
            return null;
        }
        return largestAirportName.getAirportName();

    }




}
