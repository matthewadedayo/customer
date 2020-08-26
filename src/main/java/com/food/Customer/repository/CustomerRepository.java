package com.food.Customer.repository;

import com.food.FoodModel.Customer.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adedayo
 
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    
    public Customer findById(int customerId);
    
    public Customer findByPhoneNumber(String phoneNumber);
    
    public Customer findCustomerByPhoneNumber(String phoneNumber);
    
    List <Customer> findAll();
    
}
