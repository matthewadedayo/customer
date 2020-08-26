package com.food.Customer.service.impl;

import com.food.Customer.dto.CustomerCreateDto;
import com.food.Customer.dto.CustomerResponseDto;
import com.food.Customer.dto.ServerResponse;
//import com.Food.FoodOrder.mail.EmailService;
//import com.Food.FoodOrder.mail.Mail;
import com.food.FoodModel.Customer.Customer;
import com.food.Customer.repository.CustomerRepository;
import com.food.Customer.service.CustomerService;
import com.food.Customer.utility.ServerResponseType;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
        
        //@Autowired
        //EmailService emailService;
        
	@Override
	public Customer findById(Integer customerId) {	
                
		    Optional<Customer> customer = customerRepository.findById(customerId);
                        if (customer.isPresent())
                             return customer.get();
                        else
                          return new Customer();

		}
	
	@Override
	public Customer findByPhone(String phoneNumber) {
		
		try {
			return customerRepository.findByPhoneNumber(phoneNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
                   	
                  
    @Override
    public ServerResponse create(CustomerCreateDto request) {
        
        ServerResponse response = new ServerResponse();
        Customer customer = null;
             
        String phoneNumber = request.getPhoneNumber() != null ? request.getPhoneNumber() : request.getPhoneNumber();
        String lastName = request.getLastName() != null ? request.getLastName() : request.getLastName();
        String firstName = request.getFirstName() != null ? request.getFirstName() : request.getFirstName();
        String address = request.getAddress() != null ? request.getAddress() : request.getAddress(); 
        //String email = request.getEmail() != null ? request.getEmail() : request.getEmail();
        
         //Validations
                if (phoneNumber == null || phoneNumber.isEmpty()) {
                   response.setData("");
                   response.setMessage("Please enter your phone Number");
                   response.setSuccess(false);
                   response.setStatus(ServerResponseType.FAILED);

            return response;
            }
                 if (lastName == null || lastName.isEmpty()) {
                   response.setData("");
                   response.setMessage("Please enter your Surname");
                   response.setSuccess(false);
                   response.setStatus(ServerResponseType.FAILED);

            return response;
            }
                  if (firstName == null || firstName.isEmpty()) {
                   response.setData("");
                   response.setMessage("Please enter your Name");
                   response.setSuccess(false);
                   response.setStatus(ServerResponseType.FAILED);

            return response;
            }
                   if (address == null || address.isEmpty()) {
                   response.setData("");
                   response.setMessage("Please enter your address");
                   response.setSuccess(false);
                   response.setStatus(ServerResponseType.FAILED);

            return response;
            }
                
            try {
               //User user = userRepository.findByEmail(email);
                   	
                  /*if (user == null) {
                    response.setData("");
                    response.setMessage("User does not exist");
                    response.setSuccess(false);
                    response.setStatus(ServerResponseType.FAILED);

                    return response;
			}*/
			//send mail notification on account creation
			customer = new Customer();	
                         
                        customer.setFirstName(firstName);
                        customer.setLastName(lastName);
                        customer.setPhoneNumber(phoneNumber);
                        customer.setAddress(address);
                         
			
			customerRepository.save(customer);
			
			CustomerResponseDto dto = new CustomerResponseDto();
			dto.setPhoneNumber(customer.getPhoneNumber());
			dto.setCustomerFirstName(customer.getFirstName());
			dto.setCustomerLastName(customer.getLastName());
			//dto.setCustomerEmail(user.getEmail());
			dto.setCustomerAddress(customer.getAddress());
			
			response.setData(dto);
                        response.setMessage("Customer created successfully");
                        response.setSuccess(true);
                        response.setStatus(ServerResponseType.OK);

		
		}catch(Exception e) {
			 response.setData("");
	          response.setMessage("Failed to create Customer");
	          response.setSuccess(false);
	          response.setStatus(ServerResponseType.FAILED);
	          e.printStackTrace();	
		}
			
		return response;
    }  
    
    
    @Override
    public ServerResponse getCustomerByPhoneNumber(String phoneNumber) {
        ServerResponse response =  new ServerResponse();
		
		//Validating to ensure that order code is inputed
		if(phoneNumber == null || phoneNumber.isEmpty()) {
                    
                    response.setData("");
                    response.setMessage("Please provide phone number");
                    response.setSuccess(false);
                    response.setStatus(ServerResponseType.FAILED);
                    return response;
		}
		
		
		try {
			//Confirming inputed order number exists
			Customer cust = customerRepository.findCustomerByPhoneNumber(phoneNumber);
			
		     if (cust == null) {
			response.setData("");
                        response.setMessage("Customer with phone number " + phoneNumber + " does not exist");
                        response.setSuccess(false);
                        response.setStatus(ServerResponseType.OK);
                        return response;
                        
                        
			}
			
			
			//Server response if customer with number exists 
			CustomerResponseDto dto = new CustomerResponseDto();
			dto.setCustomerFirstName(cust.getFirstName());
			dto.setCustomerLastName(cust.getLastName());
			//dto.setCustomerEmail(user.getEmail());
			dto.setPhoneNumber(cust.getPhoneNumber());
			dto.setCustomerAddress(cust.getAddress());
			
			
			response.setData(dto);
                        response.setMessage("Customer printed successfully");
                        response.setSuccess(true);
                        response.setStatus(ServerResponseType.OK);
			
			
		}catch(Exception e) {
			response.setData("");
            response.setMessage("Something went wrong");
            response.setSuccess(false);
            response.setStatus(ServerResponseType.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return response;
		}
		
		return response;
    }

    @Override
    public ServerResponse getAllCustomer() {
       ServerResponse response = new ServerResponse();
		
		try {
			List<Customer> cust = customerRepository.findAll();
			
			if (cust.size() < 1) {
				response.setData("");
				response.setMessage("customer list is empty");
				response.setSuccess(false);
				response.setStatus(ServerResponseType.NO_CONTENT);
				
				return response;
			}
			
			response.setData(cust);
			response.setMessage("Data fetched successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseType.OK);
		} catch (Exception e){
			
			response.setData("");
			response.setMessage("Failed to fetch data");
			response.setSuccess(false);
			response.setStatus(ServerResponseType.FAILED);
			return response;
		}
		
		return response;
	
    }


}
