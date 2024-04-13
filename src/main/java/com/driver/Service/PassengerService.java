package com.driver.Service;


import com.driver.Repository.PassengerRepository;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    PassengerRepository passengerRepository = new PassengerRepository();
    public void addPassengerService(Passenger passenger){
        passengerRepository.addPassenger(passenger);
    }
}
