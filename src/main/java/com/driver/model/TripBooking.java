package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class TripBooking{
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tripId;

    String fromLocation;
    String toLocation;

    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;
    int bill;
    int distanceInKm;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    Driver driver;
}