package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver{

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int driverId;
    String Mobile;
    String Password;

    @OneToOne
    @JoinColumn
    Cab cab;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    List<TripBooking> tripBookingListByDriver = new ArrayList<>();

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public List<TripBooking> getTripBookingListByDriver() {
        return tripBookingListByDriver;
    }

    public void setTripBookingListByDriver(List<TripBooking> tripBookingListByDriver) {
        this.tripBookingListByDriver = tripBookingListByDriver;
    }
}


