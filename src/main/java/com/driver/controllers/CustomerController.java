package com.driver.controllers;

import com.driver.Exception.CustomerNotFountException;
import com.driver.model.Customer;
import com.driver.model.TripBooking;
import com.driver.repository.TripBookingRepository;
import com.driver.services.CustomerService;
import com.driver.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerService;

	@PostMapping("/register")
	public ResponseEntity<Void> registerCustomer(@RequestBody Customer customer){
		customerService.register(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public void deleteCustomer(@RequestParam Integer customerId){
		try {
			customerService.deleteCustomer(customerId);
		}
		catch (CustomerNotFountException e){

		}
	}

	@PostMapping("/bookTrip")
	public ResponseEntity<Integer> bookTrip(@RequestParam Integer customerId,
											@RequestParam String fromLocation, @RequestParam String toLocation,
											@RequestParam Integer distanceInKm) throws Exception {
		try {
			TripBooking bookedTrip = customerService.bookTrip(customerId, fromLocation, toLocation, distanceInKm);
			return new ResponseEntity<>(bookedTrip.getTripId(), HttpStatus.CREATED);
		}
		catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/complete")
	public void completeTrip(@RequestParam Integer tripId){
		customerService.completeTrip(tripId);
	}

	@DeleteMapping("/cancelTrip")
	public void cancelTrip(@RequestParam Integer tripId){
		customerService.cancelTrip(tripId);
	}
}
