package com.cg.pos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.pos.dao.OrderDao;
import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
@Autowired
OrderDao dao;
	public void setDao(OrderDao dao) {
	this.dao = dao;
}
	@Override
	public PizzaDetails getOrderData(String id) throws PizzaException {
		return dao.getData(id);
		
		
	}

}
