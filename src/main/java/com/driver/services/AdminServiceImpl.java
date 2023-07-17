package com.driver.services;

import com.driver.Exception.AdminNotFoundException;
import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
       Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
       Admin admin = optionalAdmin.get();
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
        return null;
        //return driverRepository.getListOfDriver();
    }

    @Override
    public List<Customer> getListOfCustomers() {
        return null;
       // return customerRepository.ListOfCustomer();
    }
}
