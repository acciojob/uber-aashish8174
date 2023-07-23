package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver{

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int driverId;
    String mobileNumber;
    String password;

    @OneToOne
    @JoinColumn
    Cab cab;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    List<TripBooking> tripBookingListByDriver = new ArrayList<>();

}


