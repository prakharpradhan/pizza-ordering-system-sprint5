package com.cg.pos.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pos.PosApplication;
import com.cg.pos.dto.CustomerDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.service.CustomerService;
import com.cg.pos.utility.ExceptionMessage;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	CustomerDetails details;

	@Autowired
	CustomerService service;
	Logger log = org.slf4j.LoggerFactory.getLogger(PosApplication.class);

	public void setService(CustomerService service) {
		this.service = service;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public CustomerDetails getCustomerDetails(@PathVariable("id") String id) throws PizzaException {
		log.info("Customer process started:");
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerId(id);

		int flag = 0;
		try {

			details = service.ViewCustomer(customerDetails);

		} catch (PizzaException e) {
			flag = 1;
			throw new PizzaException(ExceptionMessage.MESSAGE2);
		}
		if (flag == 0) {
			log.info("successfully done");
			return details;
		} else
			return null;
	}
}
