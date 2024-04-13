package com.driver.Service;


import com.driver.Repository.AirportRepository;
import com.driver.model.Airport;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    // Here I will contain the ->  business logic of our application
    // maintain here all respective code in java
    // here I will call the repository first then get the data in list then solve according to question

    AirportRepository airportRepository = new AirportRepository();

    public void addAirportService(Airport airport){
        airportRepository.addAirport(airport);
    }

}
