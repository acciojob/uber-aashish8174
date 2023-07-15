package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

class Cab{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cabId;
    int perKmRate;
    Boolean available;

    @OneToOne(mappedBy = "cab",cascade = CascadeType.ALL)
    Driver driver;

}