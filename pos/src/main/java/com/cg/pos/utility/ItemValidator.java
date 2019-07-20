package com.cg.pos.utility;

import com.cg.pos.exceptions.PizzaException;
/**
 * validation class for adding to cart data
 * @author trainee
 *
 */
public class ItemValidator {
	/**
	 * Validation of itemid
	 * 
	 * @param itemId
	 * @throws ItemIdException
	 */
	public void itemIdValidation(String itemId) throws PizzaException {

		

		if (!(itemId.matches("^[0-9]{4}$"))) {
throw new PizzaException(ExceptionMessage.MESSAGE12);

		}

	}
	
	

}
