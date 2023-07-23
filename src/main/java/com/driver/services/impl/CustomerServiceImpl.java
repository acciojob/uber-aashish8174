package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.AdminRepository;
import com.driver.services.AdminService;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository adminRepository;

	@Autowired
	DriverRepository driverRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void adminRegister(Admin admin) {
		adminRepository.save(admin);
	}

	@Override
	public Admin updatePassword(Integer adminId, String password) {
		Admin admin = adminRepository.findById(adminId).get();

		admin.setPassWord(password);
		Admin sd = adminRepository.save(admin);
		return sd;
	}

	@Override
	public void deleteAdmin(int adminId) {
		Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
		Admin admin = optionalAdmin.get();
		adminRepository.delete(admin);
	}

	@Override
	public List<Driver> getListOfDrivers() {

		List<Driver> ListOfDrivers = driverRepository.findAll();
		return ListOfDrivers;
	}

	@Override
	public List<Customer> getListOfCustomers() {
		List<Customer> ListOfCustomers = customerRepository.findAll();
		return ListOfCustomers;
	}
}

