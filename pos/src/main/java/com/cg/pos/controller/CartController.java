package com.cg.pos.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pos.PosApplication;
import com.cg.pos.dto.CartDetails;
import com.cg.pos.dto.OrderDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.service.CartService;
import com.cg.pos.utility.ExceptionMessage;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
	@Autowired
	private CartService cart;
	Logger log = org.slf4j.LoggerFactory.getLogger(PosApplication.class);

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET, produces = "application/json")
	public CartDetails getCartDetails(@PathVariable("id") String id) throws PizzaException {
		log.info("view cart process started:");
		CartDetails cartDetails = new CartDetails();

		int flag = 0;
		try {
			cartDetails = cart.viewCart(id);
		} catch (PizzaException e) {
			flag = 1;
			throw new PizzaException(ExceptionMessage.MESSAGE6);
		}
		if (flag == 0) {
			log.info("view cart process ended:");
			return cartDetails;
		} else
			return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteCartDetails(@PathVariable("id") String id) throws PizzaException {
		log.info("delete cart process started:");
		int flag = 0;
		try {
			cart.deleteCart(id);
		} catch (PizzaException e) {
			flag = 1;
			throw new PizzaException(ExceptionMessage.MESSAGE6);

		}
		if (flag == 0) {
			log.info("delete cart process ended:");
			return "Success";
		} else
			return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
	public String updateCartDetails(@RequestBody CartDetails cartDetails) throws PizzaException {
		log.info("update cart process  process started:");
		int flag = 0;
		try {
			cart.addMore(cartDetails);
		} catch (PizzaException e) {
			flag = 1;
			throw new PizzaException(ExceptionMessage.MESSAGE6);
		}
		if (flag == 0) {
			log.info("update cart process ended:");
			return "Success";
		} else
			return null;
	}

	@RequestMapping(value = "/add/{storeName}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public int addCartDetails(@RequestBody  CartDetails cartData, @PathVariable("storeName") String name)
			throws PizzaException {
		log.info("add to cart process started:");
		int flag = 0;
		int cartId = 0;
		try {
			cartId = cart.addToCart(cartData, name);
		} catch (PizzaException e) {
			flag = 1;
			throw new PizzaException(ExceptionMessage.MESSAGE5);
		}
		if (flag == 0) {
			log.info("add to cart process ended:");
			return cartId;
		} else
			return 0;
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public int addOrderDetails(@RequestBody OrderDetails order) throws PizzaException {
		log.info("Order  process started:");
		int flag = 0;
		int orderId = 0;
		try {
			orderId = cart.confirmOrder(order);
		} catch (PizzaException e) {
			flag = 1;
			throw new PizzaException(ExceptionMessage.MESSAGE6);
		}
		if (flag == 0) {
			log.info("order process ended:");
			return orderId;
		} else
			return 0;
	}

}
