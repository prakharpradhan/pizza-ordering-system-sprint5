package com.cg.pos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pos.PosApplication;
import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.dto.StoreDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.service.PizzaService;
import com.cg.pos.service.StoreService;
import com.cg.pos.utility.ExceptionMessage;

@RestController
@RequestMapping(value = "/store")
public class StoreController {
	@Autowired
	StoreService storeService;
	@Autowired
	PizzaService pizzaService;
	Logger log = org.slf4j.LoggerFactory.getLogger(PosApplication.class);

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setPizzaService(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}

	@RequestMapping(value = "/get/{name}", method = RequestMethod.GET, produces = "application/json")
	public StoreDetails showStoreDetails(@PathVariable("name") String name) throws PizzaException {
		log.info("Store Search  process started:");
		StoreDetails details = new StoreDetails();
		details.setStoreName(name);
		StoreDetails rslt = new StoreDetails();
		int flag = 0;
		try {
			rslt = storeService.viewStoreDetails(details);
		} catch (Exception e) {
			flag = 1;
			throw new PizzaException(ExceptionMessage.MESSAGE4);
		}
		if (flag == 0) {
			log.info("Store Serach process ended:");
			return rslt;
		} else
			return null;
	}

	@RequestMapping(value = "/{storeName}", method = RequestMethod.GET, produces = "application/json")
	public List<PizzaDetails> showPizzaDetails(@PathVariable(name = "storeName") String storeName)
			throws PizzaException {
		log.info("pizza Search  process started:");
		int flag = 0;
		try {
			flag = 1;
			log.info("Pizza Serach process ended:");
			return (List<PizzaDetails>) pizzaService.viewPizza(storeName);

		} catch (PizzaException e) {
			if (flag == 1)
				throw new PizzaException(ExceptionMessage.MESSAGE4);
		}
		return null;
	}
}
