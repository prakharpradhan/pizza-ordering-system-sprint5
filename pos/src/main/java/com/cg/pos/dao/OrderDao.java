package com.cg.pos.dao;

import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.exceptions.PizzaException;

public interface OrderDao {

	PizzaDetails getData(String id) throws PizzaException;

}
