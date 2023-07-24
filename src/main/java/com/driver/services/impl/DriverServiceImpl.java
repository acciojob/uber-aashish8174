package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
	@Autowired
	DriverRepository driverRepository;
	@Autowired
	CabRepository cabRepository;
	@Override
	public void register(String mobile, String password) {
		Driver driver = new Driver();
		driver.setMobile(mobile);
		driver.setPassword(password);
		Cab cab = new Cab();
		cab.setPerKmRate(10);
		cab.setDriver(driver);
		cab.setAvailable(true);

		driverRepository.save(driver);
	}

	@Override
	public void removeDriver(int driverId) {
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		Driver driver = driverOptional.get();
		driverRepository.delete(driver);
	}

	@Override
	public void updateStatus(int driverId) {
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		Driver driver = driverOptional.get();
		Cab cab = driver.getCab();
		cab.setAvailable(false);
		cabRepository.save(cab);
	}
}
