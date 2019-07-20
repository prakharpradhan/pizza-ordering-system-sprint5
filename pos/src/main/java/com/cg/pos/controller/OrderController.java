package com.cg.pos.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pos.PosApplication;
import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.service.OrderService;
import com.cg.pos.utility.ExceptionMessage;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	OrderService order;

	public void setOrder(OrderService order) {
		this.order = order;
	}

	Logger log = org.slf4j.LoggerFactory.getLogger(PosApplication.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public String showOrderData(@PathVariable("id") String id) throws PizzaException {
		PizzaDetails details = null;
		try {
			details = order.getOrderData(id);
		} catch (PizzaException p) {
			throw new PizzaException(ExceptionMessage.MESSAGE12);
		}
		return ("customer " + id + " has added mostly " + details.getPizzaName() + " from store with id "
				+ details.getStoreID());
	}
}
