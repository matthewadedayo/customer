package com.food.Customer.service;

import com.food.Customer.dto.CustomerCreateDto;
import com.food.Customer.dto.ServerResponse;
import com.food.FoodModel.Customer.Customer;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adedayo
 */

@Service
public interface CustomerService  {
    
    public Customer findById(Integer customerId);
    
    public Customer findByPhone(String phoneNumber);
    
    public ServerResponse getCustomerByPhoneNumber(String phoneNumber);
    
    public ServerResponse getAllCustomer();
    
    public ServerResponse create(CustomerCreateDto request);
}
