package com.cg.pos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.pos.dao.CustomerDao;
import com.cg.pos.dto.CustomerDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.service.CustomerService;
import com.cg.pos.utility.CustomerValidator;
import com.cg.pos.utility.ExceptionMessage;

/**
 * Customer service class
 * 
 * @author Prakhar
 *
 */
@Service

public class CustomerServiceImpl implements CustomerService {
	/**
	 * fetching customer data
	 * 
	 * @throws SQLException
	 */

	@Autowired
	CustomerDao dao;

	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}

	@Override
	public CustomerDetails ViewCustomer(CustomerDetails customerDetails) throws PizzaException {
		CustomerValidator customerValidator = new CustomerValidator();
		boolean isValid = customerValidator.IdValidation((customerDetails.getCustomerId()));
		if (isValid) {
			return dao.viewCustomerDetails(customerDetails);
		} else
			throw new PizzaException(ExceptionMessage.MESSAGE2);
	}

}
