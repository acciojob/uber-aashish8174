package com.driver.Exception;

public class TripNotFoundException extends RuntimeException{
    public TripNotFoundException(String mass){
        super(mass);
    }
}
