package com.driver.Exception;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String massage){
        super(massage);
    }
}
