package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

//import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public
class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int adminId;

    String userName;
    String passWord;
  }