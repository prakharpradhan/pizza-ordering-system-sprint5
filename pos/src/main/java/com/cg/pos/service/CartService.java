package com.cg.pos.service;

import com.cg.pos.dto.CartDetails;
import com.cg.pos.dto.OrderDetails;
import com.cg.pos.exceptions.PizzaException;

public interface CartService {
	/*
	 * Cart Service Method
	 */
	public int addToCart(CartDetails cartDetails, String storeName) throws PizzaException;

	public int confirmOrder(OrderDetails details) throws PizzaException;

	public void addMore(CartDetails cartDetails) throws PizzaException;

	public void deleteCart(String cartId) throws PizzaException;

	public CartDetails viewCart(String id) throws PizzaException;

}
