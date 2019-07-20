package com.cg.pos.utility;

import com.cg.pos.exceptions.PizzaException;

public class CartValidator {
	/**
	 * validaton of cart id
	 * 
	 * @param cartId
	 * @throws InvalidCartException
	 */
	public boolean isCartIdValid(String cartId) throws PizzaException {

		if (!(cartId.matches("[0-9]+") && cartId.length() == 5)) {

			throw new PizzaException(ExceptionMessage.MESSAGE6);

		}
		return true;
	}
	public void qtyValidaton(String tempQty) throws PizzaException {
		
		if (tempQty.matches("^[a-zA-Z]*$") || tempQty.isEmpty()) {

			throw new PizzaException(ExceptionMessage.MESSAGE12);

		}

	}
}
