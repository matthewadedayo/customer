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
public class CustomerResponseDto {
    
    private String phoneNumber;
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    //private String customerEmail;
}