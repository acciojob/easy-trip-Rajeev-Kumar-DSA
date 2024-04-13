package com.driver.Service;


import com.driver.Repository.AirportRepository;
import com.driver.Repository.FlightRepository;
import com.driver.Repository.PassengerRepository;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlightService {
    FlightRepository flightRepository = new FlightRepository();

    PassengerRepository passengerRepository = new PassengerRepository();

    AirportRepository airportRepository = new AirportRepository();

    public double getShortestDurationBetweenCitiesService(City fromCity, City toCity){
        // first get all the flights details here
        List<Flight> flights = flightRepository.getAllFlights();

        // Initialize the maximum possible value - because finding here shortest distance
        double shortestDuration = Double.MAX_VALUE;

        // now I will iterate all possible flight then check shortest
        for(Flight flight : flights){
            //  Check if the flight is from the source city to the destination city
            // flight is present or not
            if(flight.getFromCity() == fromCity && flight.getToCity() == toCity){
                //  If it is a direct flight between the cities, update the shortest duration if needed
                shortestDuration = Math.min(shortestDuration, flight.getDuration());
            }
        }

        if(shortestDuration == Double.MAX_VALUE){
            return -1;
        }

        return shortestDuration;
    }

    public int getNumberOfPeopleOnService(Date date, String airportName){
        List<Flight> flights = flightRepository.getAllFlights();

        int totalNumberOfPeople = 0;

        for(Flight flight : flights){
            // Retrieve the airport name from the flight's departure city
            // here I will get the departure location
            String departureAirportName = flight.getFromCity().name();
            String arrivalAirportName = flight.getToCity().name();

            // Check if the departure airport name matches the given airportName
            if(departureAirportName.equals(airportName)){
                // flight's date matches the given date
                if (flight.getFlightDate().equals(date)){
                    // Increment the number of people by the number of passengers on this flight
                    totalNumberOfPeople += flight.getMaxCapacity();
                }
            }

            if(arrivalAirportName.equals(airportName)){
                if(flight.getFlightDate().equals(date)){
                    totalNumberOfPeople += flight.getMaxCapacity();
                }
            }

        }
        return totalNumberOfPeople;
    }


    // find the totalNumber  of passenger here
//    Map<Integer, Integer> numberOfPassengersMap = new HashMap<>();
//    public String bookTicket(int flightId){
//        Flight flight = flightRepository.getFlight(flightId);
//
//        if(flight == null){
//            return "FAIL";
//        }
//
//        // Increase the number of count the passenger for the flight
//        // Increment the number of passengers for the flight
//        int numberOfPassengers = numberOfPassengersMap.getOrDefault(flightId, 0);
//        int maxCapacity = flight.getMaxCapacity();
//
//        if (numberOfPassengers >= maxCapacity) {
//            return "FAILURE"; // Flight already at max capacity
//        }
//
//        numberOfPassengersMap.put(flightId, numberOfPassengers + 1);
//        return "SUCCESS"; // Ticket booked successfully
//
//    }


    public int getNumberOfPassengers(){
        return passengerRepository.getTotalNumberOfPassengers();
    }

    public int calculateFlightFareService(int flightId){
        Flight flight = flightRepository.getFlight(flightId);

        if (flight == null){
            return 0;
        }

        // Calculate fare based on the number of people who have already booked the flight
        int noOfPeopleWhoHaveAlreadyBooked = passengerRepository.getNumberOfPassengerForFlight(flightId);
        int basePrice = 3000;
        int fare = basePrice + (noOfPeopleWhoHaveAlreadyBooked * 50);

        return fare;
    }


    public String bookATicketService(int flightId, int passengerId){
        Flight flight = flightRepository.getFlight(flightId);

        if (flight == null){
            return "FAILURE";
        }

        // check the passenger exists or not
        Passenger passenger = passengerRepository.getPassenger(passengerId);
        if(passenger == null){
            return "FAILURE";
        }

        // check if flight is already completely booked - no any empty seat available
        int numberOfPassengers = passengerRepository.getTotalNumberOfPassengers();
        if(numberOfPassengers >= flight.getMaxCapacity()){
            return "FAILURE";
        }

        // able to book the ticket for the flight
        passengerRepository.bookTicket(passengerId, flightId);

        return "SUCCESS";
    }




    public String cancelATicketService(int flightId, int passengerId){
        Flight flight = flightRepository.getFlight(flightId);
        if(flight == null){
            return "FAILURE";
        }

        // check passenger exist or not
        Passenger passenger = passengerRepository.getPassenger(passengerId);
        if (passenger == null){
            return "FAILURE";
        }

        // check if the passenger has a ticket for the given flight
        int bookedFlightId = passengerRepository.getFlightIdForPassenger(passengerId);
        if (bookedFlightId != flightId){
            return "FAILURE";
        }

        // cancle the ticket for the passenger
        passengerRepository.cancleTicket(passengerId);

        return "SUCCESS";

    }

    public void addFlightService(Flight flight){
        flightRepository.addFlight(flight);
    }


    public String getAirportNameFromFlightIdService(int flightId){
        Flight flight = flightRepository.getFlight(flightId);

        if(flight == null){
            return null;  // not able to find
        }
        // Get the departure city from the flight
        City departureCity = flight.getFromCity();

        // Now, find the airport associated with the departure city
        Airport airport = findAirportByCity(departureCity);

        if (airport == null){
            return null;
        }

        return airport.getAirportName();

    }
    // Helper method to find the airport by city
    private Airport findAirportByCity(City city){
        // Assuming you have a method in AirportRepository to find airport by city
        return airportRepository.getAirportByCity(city);
    }




//    public int countOfBookingsDoneByPassengerAllCombinedService(int passengerId){
//        Passenger passenger = passengerRepository.getPassenger(passengerId);
//
//        if(passenger == null){
//            return 0;
//        }
//
//        int bookingCount = 0;
//
//        // Iterate through all flights and count bookings made by the passenger
//        for (Flight flight : flightRepository.getAllFlights()){
//            // Check if the passenger has booked the flight
//            if(flight.)
//        }
//
//    }


    public int calculateRevenueOfAFlightService(int flightId){
        Flight flight = flightRepository.getFlight(flightId);

        if(flight == null){
            return  0;
        }

        int fare = calculateFlightFareService(flightId);
        int numberOfPassengers = passengerRepository.getTotalNumberOfPassengers();

        return fare * numberOfPassengers;
    }




}
