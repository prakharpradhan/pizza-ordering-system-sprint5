package com.cg.pos.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.pos.dto.CartDetails;
import com.cg.pos.dto.CustomerDetails;
import com.cg.pos.dto.OrderDetails;
import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.dto.StoreDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.utility.ExceptionMessage;

/**
 * Cart database access class
 * 
 * @author Prakhar
 *
 */
@Repository
@Transactional

public class CartDaoImpl implements CartDao {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public int addToCart(CartDetails cart) throws PizzaException {

		int item = cart.getItemId();

		PizzaDetails details = manager.find(PizzaDetails.class, item);
		cart.setRestCharges(details.getPrice() * cart.getQuantity());
		cart.setDelievryCharges(50);
		cart.setTotal(cart.getRestCharges() + 50);
		manager.persist(cart);
		int id = cart.getCartId();
		return id;
	}

	@Override
	public int checkDb(int itemId, String storeName) throws PizzaException {
		int id = 0;
		int result = 0;
		TypedQuery<StoreDetails> quer = manager.createQuery("select s  from StoreDetails s where s.storeName=:name",
				StoreDetails.class);

		quer.setParameter("name", storeName);
		try {
			id = quer.getSingleResult().getStoreId();
		} catch (NoResultException e) {
			throw new PizzaException(ExceptionMessage.MESSAGE5);
		}
		TypedQuery<PizzaDetails> query = manager.createQuery(
				"select p  from PizzaDetails p where p.storeID=:storeId and p.itemId=:id", PizzaDetails.class);
		query.setParameter("storeId", id);
		query.setParameter("id", itemId);

		try {
			result = query.getSingleResult().getItemId();
		} catch (NoResultException e) {
			throw new PizzaException(ExceptionMessage.MESSAGE5);
		}
		return result;
	}

	@Override
	public CartDetails viewCart(int cartId) throws PizzaException {
		return manager.find(CartDetails.class, cartId);
	}

	@Override
	public int deleteCart(int cartId) throws PizzaException {

		CartDetails details = manager.find(CartDetails.class, cartId);
		if (details == null) {
			return 1;
		}
		manager.remove(details);

		return 0;
	}

	@Override
	public int updateCart(CartDetails cartDetails) throws PizzaException {
		int cartId = cartDetails.getCartId();
		int qty = cartDetails.getQuantity();
		CartDetails details = manager.find(CartDetails.class, cartId);
		if (details == null) {
			return 0;
		}
		double price = details.getRestCharges();
		details.setRestCharges((price / details.getQuantity()) * qty);
		details.setQuantity(qty);
		details.setTotal(details.getDelievryCharges() + details.getRestCharges());
		return 1;
	}

	@Override
	public int addOrder(OrderDetails orderDetails) throws PizzaException {
		CustomerDetails customerDetails = manager.find(CustomerDetails.class, orderDetails.getCustId());
		CartDetails cartDetails = manager.find(CartDetails.class, orderDetails.getCartId());
		if (customerDetails != null && cartDetails != null && cartDetails.getCustId().equals(orderDetails.getCustId())) {
			manager.persist(orderDetails);
			return orderDetails.getOrderId();

		}
		return 0;

	}

}