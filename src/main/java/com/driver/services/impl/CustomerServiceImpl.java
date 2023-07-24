package com.driver.services.impl;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.model.TripBooking;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.driver.model.TripStatus.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TripBookingRepository tripBookingRepository;
    @Autowired
    DriverRepository driverRepository ;
    @Override
    public void register(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if(!optionalCustomer.isPresent()){
//            throw new CustomerNotFountException("Customer doesn't Exist");
//        }
        Customer customer = optionalCustomer.get();
        customerRepository.delete(customer);
    }

    @Override
    public TripBooking bookTrip(int customerId, String fromLocation,
                                String toLocation, int distanceInKm) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if (!optionalCustomer.isPresent()){
//            throw new CustomerNotFountException("customer doesn't exist");
//        }
        Customer customer = optionalCustomer.get();

        Driver driver = null;
        List<Driver> allDriver = driverRepository.findAll();
        for(Driver drv : allDriver) {
            if(drv.getCab().getAvailable()) {
                if (driver == null || driver.getDriverId() > drv.getDriverId()) {
                    driver = drv;
                }
            }
        }
        if(driver == null){
            throw new Exception("No cab available!");
        }

        TripBooking tripBooking = new TripBooking();


        tripBooking.setCustomer(customer);
        tripBooking.setStatus(CONFIRMED);
        tripBooking.setFromLocation(fromLocation);
        tripBooking.setToLocation(toLocation);
        tripBooking.setDistanceInKm(distanceInKm);
        driver.getCab().setAvailable(false);

        tripBooking.setBill(driver.getCab().getPerKmRate()*distanceInKm);
        customer.getTripBookingList().add(tripBooking);
        driver.getTripBookingListByDriver().add(tripBooking);
        driverRepository.save(driver);
        customerRepository.save(customer);
        tripBookingRepository.save(tripBooking);
        return tripBooking;
    }

    @Override
    public void cancelTrip(Integer tripId) {
        Optional<TripBooking> optionalTripBooking = tripBookingRepository.findById(tripId);
//        if (!optionalTripBooking.isPresent()){
//            throw new TripNotFoundException("no such trip");
//        }
        TripBooking tripBooking = optionalTripBooking.get();
        tripBooking.setStatus(CANCELED);
        tripBooking.setBill(0);
        Driver driver = tripBooking.getDriver();
        driver.getCab().setAvailable(true);

        driverRepository.save(driver);
        tripBookingRepository.save(tripBooking);
    }

    @Override
    public void completeTrip(Integer tripId) {
        Optional<TripBooking> optionalTripBooking = tripBookingRepository.findById(tripId);
//        if (!optionalTripBooking.isPresent()){
//            throw new TripNotFoundException("no such trip");
//        }
        TripBooking tripBooking = optionalTripBooking.get();
        tripBooking.setStatus(COMPLETED);
        Driver driver = tripBooking.getDriver();
        driver.getCab().setAvailable(true);
        driverRepository.save(driver);
        tripBookingRepository.save(tripBooking);
    }
}