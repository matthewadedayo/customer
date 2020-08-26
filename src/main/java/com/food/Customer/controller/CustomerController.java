package com.food.Customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.Customer.dto.ServerResponse;
import com.food.Customer.dto.CustomerCreateDto;
import com.food.Customer.utility.ServerResponseType;
import com.food.Customer.service.CustomerService;
import com.food.FoodModel.Customer.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/*
 * Developer's  account endpoint manager 
 */
@Slf4j
@RestController
@RequestMapping(value = "/customers", produces = "application/json")
@Api(tags = "Customer Management", description = "Endpoint")
public class CustomerController {
	
	
	@Autowired
	CustomerService customerService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	
	@ApiOperation(value = "Account creation", response = ServerResponse.class)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createCustomer(@RequestBody CustomerCreateDto request){
		
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = customerService.create(request);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
                        response.setMessage("An error occured");
                        response.setSuccess(false);
                        response.setStatus(ServerResponseType.FAILED);
            
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
	
	@ApiOperation(value = "List all customer", response = ServerResponse.class)
       @RequestMapping(value = "/all", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<?> getAllOrders(@RequestHeader("Authorization")  String authorization){
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = customerService.getAllCustomer();
		
		} catch (Exception e) {
			response.setData("User account verification error" + e.getMessage());
			response.setMessage("User account verification error");
	        response.setSuccess(false);
            response.setStatus(ServerResponseType.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}
       
       
       @RequestMapping(value = "/{id}", method = RequestMethod.GET)
       public Customer getCustomer(@PathVariable(value = "id") Integer customerId){        
            	Customer customer = customerService.findById(customerId);
                log.info("Customer Available");
			return customer;
                	
		
         }
        
 
}
