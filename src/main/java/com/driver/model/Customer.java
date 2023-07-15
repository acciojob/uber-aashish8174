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
public
class Customer{
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerId;
    String name;
    String mobileNumber;
    String password;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<TripBooking> tripBookingList = new ArrayList<>();

}