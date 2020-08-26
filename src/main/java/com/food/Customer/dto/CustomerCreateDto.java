package com.food.Customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Adedayo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDto {
    
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String address;
    //private String email;
}