package com.driver.services;

import com.driver.Exception.CustomerNotFountException;
import com.driver.Exception.TripNotFoundException;
import com.driver.model.Customer;
import com.driver.model.TripBooking;
import com.driver.repository.CustomerRepository;
import com.driver.repository.TripBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.driver.model.TripStatus.COMPLETED;
import static com.driver.model.TripStatus.CONFIRMED;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TripBookingRepository tripBookingRepository;
    @Override
    public void register(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(!optionalCustomer.isPresent()){
            throw new CustomerNotFountException("Customer doesn't Exist");
        }
        Customer customer = optionalCustomer.get();
        customerRepository.delete(customer);
    }

    @Override
    public TripBooking bookTrip(int customerId, String fromLocation,
                                String toLocation, int distanceInKm) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()){
            throw new CustomerNotFountException("customer doesn't exist");
        }
        Customer customer = optionalCustomer.get();
        TripBooking tripBooking = new TripBooking();
        tripBooking.setCustomer(customer);
        tripBooking.setTripStatus(CONFIRMED);
        tripBooking.setFromLocation(fromLocation);
        tripBooking.setToLocation(toLocation);
        tripBooking.setDistanceInKm(distanceInKm);
        return tripBookingRepository.save(tripBooking);
    }

    @Override
    public void cancelTrip(Integer tripId) {
        Optional<TripBooking> optionalTripBooking = tripBookingRepository.findById(tripId);
        if (!optionalTripBooking.isPresent()){
            throw new TripNotFoundException("no such trip");
        }
        TripBooking tripBooking = optionalTripBooking.get();

        tripBookingRepository.delete(tripBooking);
    }

    @Override
    public void completeTrip(Integer tripId) {
        Optional<TripBooking> optionalTripBooking = tripBookingRepository.findById(tripId);
        if (!optionalTripBooking.isPresent()){
            throw new TripNotFoundException("no such trip");
        }
        TripBooking tripBooking = optionalTripBooking.get();
        tripBooking.setTripStatus(COMPLETED);
        tripBookingRepository.save(tripBooking);
    }
}
