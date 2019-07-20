package com.cg.pos.service;

import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.exceptions.PizzaException;

public interface OrderService {

	PizzaDetails getOrderData(String id) throws PizzaException;

}
